import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MotivoService } from 'src/app/services/motivo.service';
import * as moment from 'moment';
import { SelectItem } from 'src/app/models/SelectItem';
import { MessageService } from 'primeng/api';
import { MessagemUtils } from 'src/app/shared/mensagens-uteis';



@Component({
  selector: 'app-motivo-cadastro',
  templateUrl: './motivo-cadastro.component.html',
  styleUrls: ['./motivo-cadastro.component.css']
})
export class MotivoCadastroComponent implements OnInit {

  public motivoForm: FormGroup;
  public fb: FormBuilder = new FormBuilder();

  /*cargos: SelectItem[] = [];*/

  constructor(
    private service: MotivoService,
    private router: Router,
    /*private cargoService: CargoService,*/
    private mensagem: MessageService) {

  }

  ngOnInit(): void {
    this.criaFormulario();
    /*this.listaCargos();*/

  }

  /*public listaCargos(): void {
    this.cargoService.buscarTodos().subscribe(res => {
      this.cargos = [{
        label: '--Selecione um cargo--',
        value: null
      } as SelectItem].concat(res);
    })
  }*/

  public criaFormulario(): void {
    this.motivoForm = this.fb.group({
      id: [null],
      titulo: ['', Validators.required],
      descricao: ['', Validators.required],      
    });
  }

  public cancelar(): void {
    this.router.navigateByUrl('motivos')
  }


  public criar(): void {
   
    if(!this.motivoForm.valid) {
      this.mensagem.add({ severity: 'error', summary: MessagemUtils.TITULO_DADOS_INVALIDOS, detail: MessagemUtils.MENSAGEM_ERRO_PREENCHIMENTO})
      return;
    }
    this.salvarMotivo();
  }

  public salvarMotivo(): void {
    this.service.inserir(this.motivoForm.value).subscribe(() => {
      this.mensagem.add({ severity: 'success', summary: MessagemUtils.TITULO_SUCESSO, detail: MessagemUtils.MENSAGEM_DADOS_SALVOS });
      this.router.navigateByUrl('motivos')
    })
  }


}
