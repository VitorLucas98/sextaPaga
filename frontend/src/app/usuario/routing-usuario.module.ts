import { UsuarioCadastroComponent } from './usuario-cadastro/usuario-cadastro.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsuarioListComponent } from './usuario-list/usuario-list.component';



const routes: Routes = [
    { path: '', component: UsuarioListComponent },
    {path: 'criar', component: UsuarioCadastroComponent, data: { breadcrumb: 'criar'}},

];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class RoutingUsuarioModule { }
