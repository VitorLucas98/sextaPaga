import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SelectItem } from 'src/app/models/SelectItem';
import { EventoService } from 'src/app/services/evento.service';
import { MotivoService } from 'src/app/services/motivo.service';
import { SituacaoService } from 'src/app/services/situacao.service';
import { UsuarioService } from 'src/app/services/usuario.service';

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
  todosUsuarios: SelectItem[] = [];
  usuariosSelecionados: SelectItem[] = [];

  constructor(
    private service: EventoService, 
    private motivoService : MotivoService, 
    private situacaoService : SituacaoService, 
    private usuarioService : UsuarioService) { }

  ngOnInit(): void {
    this.listaMotivos();
    this.listaSituacoes();
    this.buscarTodosUsuario();
  }

  public buscarTodosUsuario(){
    this.usuarioService.buscarTodosSelect().subscribe(result => {
      this.todosUsuarios = result;
      console.log(this.todosUsuarios);
    })
  }
  
  
  public criaFormulario(): void {
    this.eventoForm = this.fb.group({
    id: [null],
    nome: ['', Validators.required],
    dataEvento: ['', Validators.required],
    valor: ['', Validators.required],
    motivo: [null, Validators.required], 
    situacao: [null, Validators.required],
    usuarios: [null, Validators.required]
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
