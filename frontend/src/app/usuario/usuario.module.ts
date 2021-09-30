import { UsuarioListComponent } from './usuario-list/usuario-list.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppRoutingUsuarioModule } from './app-routing-usuario.module';
import { SharedModule } from '../shared/shared.module';



@NgModule({
  declarations: [UsuarioListComponent],
  imports: [
    CommonModule,
    AppRoutingUsuarioModule,
    SharedModule
  ]
})
export class UsuarioModule { }
