import { CrudOperationEnum } from 'src/app/enums/Crud-Operation.enum';
import { Evento } from './../../models/Evento';
import { MotivoModule } from './../../motivo/motivo.module';
import { MotivoService } from './../../services/motivo.service';
import { Motivo } from './../../models/Motivo';
import { EventoService } from './../../services/evento.service';
import { Component, OnInit } from '@angular/core';
import { EventoListagem } from '../../models/Evento';
import { Router } from '@angular/router';
import { MotivoService } from 'src/app/services/motivo.service';
import { MessagemUtils } from 'src/app/shared/mensagens-uteis';
import { MessageService } from 'primeng';

@Component({
  selector: 'app-evento-list',
  templateUrl: './evento-list.component.html',
  styleUrls: ['./evento-list.component.css']
})
export class EventoListComponent implements OnInit {

  eventos: EventoListagem[] = [];
  eventoBuscado: Evento;
  public isDialogVisible: boolean = false;
  private modoCrud: CrudOperationEnum = CrudOperationEnum.CREATE; 


  constructor(private service : EventoService, 
              private router: Router, 
              private mensagem: MessageService) { }



  ngOnInit(): void {
    this.buscarTodos();
  }

  cadastrar(): void{
    this.router.navigateByUrl('eventos/criacao')
  }

  buscarTodos(): void{
    this.service.buscarTodos().subscribe( res => {
      this.eventos = res;
    })
  }

  public visualizar(eventoId: number): void {
    this.service.buscarPorId(eventoId).subscribe(res => {
      this.eventoBuscado = res
      this.showDialog(CrudOperationEnum.READ);
    })

  }

  public adiarEvento(eventoId: number): void{
    this.service.adiarEvento(eventoId).subscribe(() =>{
      this.mensagem.add({ severity: 'success', summary: MessagemUtils.TITULO_SUCESSO, detail: MessagemUtils.MENSAGEM_DADOS_SALVOS });
      this.buscarTodos();
    })
  }

  public editar(eventoId: number): void {
    this.service.buscarPorId(eventoId).subscribe(res => {
      this.eventoBuscado = res
      this.showDialog(CrudOperationEnum.UPDATE);
    })
  }

  public getModoCrud(): CrudOperationEnum {
    return this.modoCrud;
  }

  public showDialog(operacao: CrudOperationEnum): void {
    this.modoCrud = operacao;
    this.isDialogVisible = true;
  }

}
