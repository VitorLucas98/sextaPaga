import { UsuarioCadastroComponent } from './usuario-cadastro/usuario-cadastro.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsuarioListComponent } from './usuario-list/usuario-list.component';



const routes: Routes = [
    { path: '', component: UsuarioListComponent },
    {path: 'criacao', component: UsuarioCadastroComponent, data: { breadcrumb: 'criacao'}}

];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingUsuarioModule { }
