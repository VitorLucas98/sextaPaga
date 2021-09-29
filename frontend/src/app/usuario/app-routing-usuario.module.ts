import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsuarioListComponent } from '../view/components/usuario/usuario-list/usuario-list.component';

const routes: Routes = [
    { path: '', component: UsuarioListComponent }

];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingUsuarioModule { }
