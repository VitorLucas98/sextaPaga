import { UsuarioListComponent } from './usuario-list/usuario-list.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { UsuarioCadastroComponent } from './usuario-cadastro/usuario-cadastro.component';
import { RoutingUsuarioModule } from './routing-usuario.module';
import { ReactiveFormsModule } from '@angular/forms';




@NgModule({
  declarations: [UsuarioListComponent, UsuarioCadastroComponent],
  imports: [
    CommonModule,
    RoutingUsuarioModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class UsuarioModule { }
