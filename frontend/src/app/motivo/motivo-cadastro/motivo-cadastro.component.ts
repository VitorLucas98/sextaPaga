import { Observable } from 'rxjs';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MotivoService } from 'src/app/services/motivo.service';
import * as moment from 'moment';
import { SelectItem } from 'src/app/models/SelectItem';
import { MessageService } from 'primeng/api';
import { MessagemUtils } from 'src/app/shared/mensagens-uteis';
import { Motivo } from 'src/app/models/Motivo';
import { CrudOperationEnum } from 'src/app/enums/Crud-Operation.enum';



@Component({
  selector: 'app-motivo-cadastro',
  templateUrl: './motivo-cadastro.component.html',
  styleUrls: ['./motivo-cadastro.component.css']
})
export class MotivoCadastroComponent implements OnInit {



  public motivoForm: FormGroup;
  public fb: FormBuilder = new FormBuilder();

  
  @Input() motivo: Motivo;
  @Input() modoCrud: CrudOperationEnum = CrudOperationEnum.CREATE;
  @Output() onCancel: EventEmitter<any> = new EventEmitter();
  @Output() novaBusca: EventEmitter<any> = new EventEmitter();
 


  constructor(
    private service: MotivoService,
    private router: Router,
    private mensagem: MessageService) {

  }

  ngOnInit(): void {
    this.criaFormulario();
    this.preencherFormulario();
    this.estadoFormulario(CrudOperationEnum.UPDATE == this.modoCrud || CrudOperationEnum.CREATE == this.modoCrud);
  }

  public criaFormulario(): void {
    this.motivoForm = this.fb.group({
      id: [null],
      titulo: ['', Validators.required],
      descricao: ['', Validators.required]      
    });
  }

  preencherFormulario(): void {
    this.motivoForm.get('id').setValue(this.motivo.id);
    this.motivoForm.get('titulo').setValue(this.motivo.titulo);
    this.motivoForm.get('descricao').setValue(this.motivo.descricao);
  }

  public estadoFormulario(habilitado: boolean): void {
    habilitado ?  this.motivoForm.enable() : this.motivoForm.disable();
  }

  public cancelar(): void {
    this.onCancel.emit()
    this.router.navigateByUrl('motivos')
  }

  public getTitulo(): string {
    switch (this.modoCrud) {
      case CrudOperationEnum.CREATE: return 'CADASTRO DE MOTIVO';
      case CrudOperationEnum.UPDATE: return 'EDIÇÃO DE MOTIVO';
      case CrudOperationEnum.READ: return 'VISUALIZAÇÃO DE MOTIVO';
    }
  }

  public persistir(): Observable<Motivo> {
    switch (this.modoCrud) {
      case CrudOperationEnum.CREATE:
        this.criarMotivo();
        return
      case CrudOperationEnum.UPDATE:
        this.atualizarMotivo();
        return
      
    }
    throw Error('Não foi possível persistir os dados devido à falta de uma estratégia');
  }

  public criarMotivo(): void {
    this.salvar();
    this.service.inserir(this.motivoForm.value).subscribe(() => {
      this.mensagem.add({ severity: 'success', summary: MessagemUtils.TITULO_SUCESSO, detail: MessagemUtils.MENSAGEM_DADOS_SALVOS });
      this.router.navigateByUrl('motivos')
      this.onCancel.emit();
    })
  }

  public atualizarMotivo(): void {
    this.salvar();
    this.service.atualizar(this.motivoForm.value, this.motivoForm.value.id).subscribe(() => {
      this.mensagem.add({ severity: 'success', summary: MessagemUtils.TITULO_SUCESSO, detail: MessagemUtils.MENSAGEM_DADOS_SALVOS });
      this.onCancel.emit();
      this.novaBusca.emit();
    })
  }

  public salvar(): void {
        if (!this.motivoForm.valid) {
      this.mensagem.add({ severity: 'error', summary: MessagemUtils.TITULO_DADOS_INVALIDOS, detail: MessagemUtils.MENSAGEM_ERRO_PREENCHIMENTO })
      return;
    }
  }

  public deletarMotivo(): void {
    if (!this.motivoForm.valid) {
      this.mensagem.add({ severity: 'success', summary: MessagemUtils.TITULO_DADOS_EXCLUIDOS, detail: MessagemUtils.MENSAGEM_DADOS_DELETADOS });
      return;
    };
  
  }

}
