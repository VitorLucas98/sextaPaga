import { UsuarioListComponent } from './usuario-list/usuario-list.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppRoutingUsuarioModule } from './app-routing-usuario.module';
import { SharedModule } from '../shared/shared.module';
import { UsuarioCadastroComponent } from './usuario-cadastro/usuario-cadastro.component';



@NgModule({
  declarations: [UsuarioListComponent, UsuarioCadastroComponent],
  imports: [
    CommonModule,
    AppRoutingUsuarioModule,
    SharedModule
  ]
})
export class UsuarioModule { }
