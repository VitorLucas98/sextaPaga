import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SelectItem } from 'src/app/models/SelectItem';
import { EventoService } from 'src/app/services/evento.service';
import { MotivoService } from 'src/app/services/motivo.service';
import { SituacaoService } from 'src/app/services/situacao.service';

@Component({
  selector: 'app-evento-cadastro',
  templateUrl: './evento-cadastro.component.html',
  styleUrls: ['./evento-cadastro.component.css']
})
export class EventoCadastroComponent implements OnInit {

  public eventoForm: FormGroup;
  public fb: FormBuilder = new FormBuilder();

  motivos: SelectItem[] = [];
  situacoes : SelectItem[] = [];

  constructor(private service: EventoService, private motivoService : MotivoService, private situacaoService : SituacaoService) { }

  ngOnInit(): void {
    this.listaMotivos();
    this.listaSituacoes();
  }

  
  public criaFormulario(): void {
    this.eventoForm = this.fb.group({
    
    });
  }

  public listaMotivos(): void {
    this.motivoService.buscarTodosSelect().subscribe(res => {
      this.motivos = [{
        label: '--Selecione um motivo--',
        value: null
      } as SelectItem].concat(res);
    })
  }

  public listaSituacoes(): void {
    this.situacaoService.buscarTodos().subscribe(res => {
      this.situacoes = [{
        label: '--Selecione uma situação--',
        value: null
      } as SelectItem].concat(res);
    })
  }

}
