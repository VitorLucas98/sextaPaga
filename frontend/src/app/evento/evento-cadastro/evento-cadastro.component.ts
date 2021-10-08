import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng';
import * as moment from 'moment';
import { SelectItem } from 'src/app/models/SelectItem';
import { EventoService } from 'src/app/services/evento.service';
import { MotivoService } from 'src/app/services/motivo.service';
import { SituacaoService } from 'src/app/services/situacao.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { MessagemUtils } from 'src/app/shared/mensagens-uteis';
import { Router } from '@angular/router';
import { Evento } from 'src/app/models/Evento';
import { Input } from '@angular/core';
import { CrudOperationEnum } from 'src/app/enums/Crud-Operation.enum';
import { FormataDataPipe } from 'src/app/pipes/formata-data.pipe';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-evento-cadastro',
  templateUrl: './evento-cadastro.component.html',
  styleUrls: ['./evento-cadastro.component.css']
})
export class EventoCadastroComponent implements OnInit {

  public eventoForm: FormGroup;
  public fb: FormBuilder = new FormBuilder();

  motivos: SelectItem[] = [];
  situacoes: SelectItem[] = [];
  todosUsuarios: SelectItem[] = [];
  usuariosSelecionados: SelectItem[] = [];

  @Input() evento: Evento;
  @Input() modoCrud: CrudOperationEnum = CrudOperationEnum.CREATE;
  @Output() onCancel: EventEmitter<any> = new EventEmitter();
  @Output() novaBusca: EventEmitter<any> = new EventEmitter();

  constructor(
    private service: EventoService,
    private motivoService: MotivoService,
    private situacaoService: SituacaoService,
    private usuarioService: UsuarioService,
    private router: Router,
    private mensagem: MessageService,
    private formataData: FormataDataPipe) { }

  ngOnInit(): void {
    this.criaFormulario();
    this.listaMotivos();
    this.listaSituacoes();
    this.buscarTodosUsuario();
    this.preencherFormulario();
    this.estadoFormulario(CrudOperationEnum.UPDATE == this.modoCrud || CrudOperationEnum.CREATE == this.modoCrud);
  }

  public getTitulo(): string {
    switch (this.modoCrud) {
      case CrudOperationEnum.CREATE: return 'CADASTRO DE EVENTO';
      case CrudOperationEnum.UPDATE: return 'EDIÇÃO DE EVENTO';
      case CrudOperationEnum.READ: return 'VISUALIZAÇÃO DE EVENTO';
      case CrudOperationEnum.DELETE: return 'DELETAR EVENTO';
    }
  }

  public getButton(): string {
    switch (this.modoCrud) {
      case CrudOperationEnum.CREATE: return 'CADASTRAR';
      case CrudOperationEnum.UPDATE: return 'ATUALIZAR';
      case CrudOperationEnum.DELETE: return 'DELETAR';
  }
}

  public buscarTodosUsuario() {
    this.usuarioService.buscarTodosSelect().subscribe(result => {
      this.todosUsuarios = result;
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
      usuarios: [null]
    });
  }

  preencherFormulario(): void {
    this.eventoForm.get('id').setValue(this.evento.id);
    this.eventoForm.get('nome').setValue(this.evento.nome);
    this.eventoForm.get('dataEvento').setValue(this.formataData.transform(this.evento.dataEvento));
    this.eventoForm.get('valor').setValue(this.evento.valor);
    this.eventoForm.get('motivo').setValue(this.evento.motivo.value);
    this.eventoForm.get('situacao').setValue(this.evento.situacao.value);
    this.eventoForm.get('usuarios').setValue(this.usuariosSelecionados)
  }

  public estadoFormulario(habilitado: boolean): void {
    habilitado ? this.eventoForm.enable() : this.eventoForm.disable();
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

  public persistir(): Observable<Evento> {
    switch (this.modoCrud) {
      case CrudOperationEnum.CREATE:
        this.criarEvento();
        return
      case CrudOperationEnum.UPDATE:
        this.atualizarEvento();
        return
      case CrudOperationEnum.DELETE:
        this.deletarEvento();
        return
    }
    throw Error('Não foi possível persistir os dados devido à falta de uma estratégia');
  }

  public criarEvento(): void {
    this.validadorFormulario();
    this.service.inserir(this.eventoForm.value).subscribe(() => {
      this.mensagem.add({ severity: 'success', summary: MessagemUtils.TITULO_SUCESSO, detail: MessagemUtils.MENSAGEM_DADOS_SALVOS });
      this.router.navigateByUrl('eventos')
    }, erros => {
      console.log(erros);
    })
  }

  public atualizarEvento(): void {
    this.validadorFormulario();
    this.service.atualizar(this.eventoForm.value, this.eventoForm.value.id).subscribe(() => {
      this.mensagem.add({ severity: 'success', summary: MessagemUtils.TITULO_SUCESSO, detail: MessagemUtils.MENSAGEM_DADOS_SALVOS });
      this.onCancel.emit();
      this.novaBusca.emit();
    }, () => {
      this.mensagem.add({ severity: 'error', summary: MessagemUtils.TITULO_DADOS_INVALIDOS, detail: MessagemUtils.MENSAGEM_ERRO_PREENCHIMENTO });
    })
  }

  public deletarEvento(): void{
    this.service.deletar(this.eventoForm.value.id).subscribe(() =>{
      this.mensagem.add({ severity: 'success', summary: MessagemUtils.TITULO_DELETADO_SUCESSO, detail: "Evento " + MessagemUtils.MENSAGEM_DADOS_DELETADOS });
      this.onCancel.emit();
      this.novaBusca.emit();
    })
  }

  public validadorFormulario(): void {
    this.formatarData();
    this.formataSituacao();
    this.formataMotivo();
  }
  private formatarData(): void {
    let data: moment.Moment = moment.utc(this.eventoForm.value.dataEvento).local();
    this.eventoForm.value.dataEvento = data.format('DD/MM/YYYY');
  }

  private formataMotivo(): void {
    const motivoId = this.eventoForm.value.motivo;
    this.eventoForm.value.motivo = { value: motivoId };
  }
  private formataSituacao(): void {
    const situacaoId = this.eventoForm.value.situacao;
    this.eventoForm.value.situacao = { value: situacaoId };
  }
  public cancelarEvento(): void {
    this.onCancel.emit();
    this.router.navigateByUrl('eventos')
  }

}
