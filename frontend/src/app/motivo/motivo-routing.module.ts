import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MotivoCadastroComponent } from './motivo-cadastro/motivo-cadastro.component';
import { MotivoListComponent } from './motivo-list/motivo-list.component';

const routes: Routes = [
  {path: '', component: MotivoListComponent },
  {path: 'criar', component: MotivoCadastroComponent, data: {breadcrumb: 'criar'}},
];


@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class MotivoRoutingModule { }
