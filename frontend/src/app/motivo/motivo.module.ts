import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MotivoRoutingModule } from './motivo-routing.module';
import { MotivoCadastroComponent } from './motivo-cadastro/motivo-cadastro.component';
import { MotivoListComponent } from './motivo-list/motivo-list.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [ MotivoListComponent, MotivoCadastroComponent],
  imports: [
    CommonModule,
    MotivoRoutingModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class MotivoModule { }
