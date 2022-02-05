import { Observable } from 'rxjs';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsuarioService } from 'src/app/services/usuario.service';
import * as moment from 'moment';
import { CargoService } from 'src/app/services/cargo.service';
import { SelectItem } from 'src/app/models/SelectItem';
import { MessageService } from 'primeng/api';
import { MessagemUtils } from 'src/app/shared/mensagens-uteis';
import { Usuario } from 'src/app/models/Usuario';
import { CrudOperationEnum } from 'src/app/enums/Crud-Operation.enum';
import { FormataDataPipe } from 'src/app/pipes/formata-data.pipe';

@Component({
  selector: 'app-usuario-cadastro',
  templateUrl: './usuario-cadastro.component.html',
  styleUrls: ['./usuario-cadastro.component.css']
})
export class UsuarioCadastroComponent implements OnInit {

  public usuarioForm: FormGroup;
  public fb: FormBuilder = new FormBuilder();


  cargos: SelectItem[] = [];

  @Input() usuario: Usuario;
  @Input() modoCrud: CrudOperationEnum = CrudOperationEnum.CREATE;
  @Output() onCancel: EventEmitter<any> = new EventEmitter();
  @Output() novaBusca: EventEmitter<any> = new EventEmitter();


  constructor(
    private service: UsuarioService,
    private router: Router,
    private cargoService: CargoService,
    private mensagem: MessageService,
    private formaData : FormataDataPipe) {

  }

  ngOnInit(): void {
    this.criaFormulario();
    this.listaCargos();
    this.preencherFormulario();
    this.estadoFormulario(CrudOperationEnum.UPDATE == this.modoCrud || CrudOperationEnum.CREATE == this.modoCrud);
  }

  public listaCargos(): void {
    this.cargoService.buscarTodos().subscribe(res => {
      this.cargos = [{
        label: '--Selecione um cargo--',
        value: null
      } as SelectItem].concat(res);
    })
  }

  public criaFormulario(): void {
    this.usuarioForm = this.fb.group({
      id: [null],
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      dataNascimento: ['', Validators.required],
      cpf: ['', Validators.required],
      telefone: [''],
      status: [''],
      cargo: [null, Validators.required]
    });
  }


  preencherFormulario(): void {
    this.usuarioForm.get('id').setValue(this.usuario.id);
    this.usuarioForm.get('nome').setValue(this.usuario.nome);
    this.usuarioForm.get('cpf').setValue(this.usuario.cpf);
    this.usuarioForm.get('dataNascimento').setValue(this.formaData.transform(this.usuario.dataNascimento));
    this.usuarioForm.get('email').setValue(this.usuario.email);
    this.usuarioForm.get('telefone').setValue(this.usuario.telefone);
    this.usuarioForm.get('status').setValue(this.usuario.status);
    this.usuarioForm.get('cargo').setValue(this.usuario.cargo.value);
  }

  public estadoFormulario(habilitado: boolean): void {
    habilitado ?  this.usuarioForm.enable() : this.usuarioForm.disable();
  }

  public cancelar(): void {
    this.onCancel.emit()
    this.router.navigateByUrl('usuarios')
  }

  public getTitulo(): string {
    switch (this.modoCrud) {
      case CrudOperationEnum.CREATE: return 'CADASTRO DE USUÁRIO';
      case CrudOperationEnum.UPDATE: return 'EDIÇÃO DE USUÁRIO';
      case CrudOperationEnum.READ: return 'VISUALIZAÇÃO DE USUÁRIO';
    }
  }
  public getButton(): string {
    switch (this.modoCrud) {
      case CrudOperationEnum.CREATE: return 'CADASTRAR';
      case CrudOperationEnum.UPDATE: return 'ATUALIZAR';
    }
  }

  public persistir(): Observable<Usuario> {
    switch (this.modoCrud) {
      case CrudOperationEnum.CREATE:
        this.criarUsuario();
        return
      case CrudOperationEnum.UPDATE:
        this.atualizarUsuario();
        return
    }
    throw Error('Não foi possível persistir os dados devido à falta de uma estratégia');
  }

  public criarUsuario(): void {
    this.salvar();
    this.service.inserir(this.usuarioForm.value).subscribe(() => {
      this.mensagem.add({ severity: 'success', summary: MessagemUtils.TITULO_SUCESSO, detail: MessagemUtils.MENSAGEM_DADOS_SALVOS });
      this.router.navigateByUrl('usuarios')
      this.onCancel.emit();
    }, erros => {
      this.mensagem.add({ severity: 'error', summary: erros.error.erro, detail: erros.error.mensagem });
      console.log(erros);
    })
  }

  public atualizarUsuario(): void {
    this.salvar();
    this.service.atualizar(this.usuarioForm.value, this.usuarioForm.value.id).subscribe(() => {
      this.mensagem.add({ severity: 'success', summary: MessagemUtils.TITULO_SUCESSO, detail: MessagemUtils.MENSAGEM_DADOS_SALVOS });
      this.onCancel.emit();
      this.novaBusca.emit();
    })
  }

  public salvar(): void {
    this.formatarData();
    this.formataCargo();
    if (!this.usuarioForm.valid) {
      this.mensagem.add({ severity: 'error', summary: MessagemUtils.TITULO_DADOS_INVALIDOS, detail: MessagemUtils.MENSAGEM_ERRO_PREENCHIMENTO })
      return;
    }
  }

  public formatarData(): void {
    let data: moment.Moment = moment.utc(this.usuarioForm.value.dataNascimento).local();
    this.usuarioForm.value.dataNascimento = data.format('DD/MM/YYYY');
  }

  public formataCargo(): void {
    const cargoId = this.usuarioForm.value.cargo;
    this.usuarioForm.value.cargo = { value: cargoId };
  }
}
