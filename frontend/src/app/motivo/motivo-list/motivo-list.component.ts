import { MotivoService } from './../../services/motivo.service';
import { Component, OnInit } from '@angular/core';
import { Motivo } from 'src/app/models/Motivo';
import { MessageService } from 'primeng';
import { Router } from '@angular/router';
import { CrudOperationEnum } from 'src/app/enums/Crud-Operation.enum';

@Component({
  selector: 'app-motivo-list',
  templateUrl: './motivo-list.component.html',
  styleUrls: ['./motivo-list.component.css']
})
export class MotivoListComponent implements OnInit {

  motivos: Motivo[] = [];
  motivoBuscado: Motivo;
  public isDialogVisible: boolean = false;
  private modoCrud: CrudOperationEnum = CrudOperationEnum.READ;
  
  

  constructor(private service: MotivoService, private router: Router) { }

  ngOnInit(): void {
    this.buscarTodos();
  }

  cadastrar(): void{
    this.router.navigateByUrl('motivos/criar')
  }

  buscarTodos(): void {
    this.service.buscarTodos().subscribe(res => {
      this.motivos = res;
    })
  }
  
  public visualizar(motivoId: number): void {
    this.service.buscarPorId(motivoId).subscribe(res => {
      this.motivoBuscado = res
      this.showDialog(CrudOperationEnum.READ);
    })

  }

  public editar(motivoId: number): void {
    this.service.buscarPorId(motivoId).subscribe(res => {
      this.motivoBuscado = res
      this.showDialog(CrudOperationEnum.UPDATE);
    })
  }

  public deletar(motivoId: number): void {
    this.service.buscarPorId(motivoId).subscribe(res => {
      this.motivoBuscado = res
      this.showDialog(CrudOperationEnum.DELETE);
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
