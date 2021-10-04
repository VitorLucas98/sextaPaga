import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EventoCadastroComponent } from './evento-cadastro/evento-cadastro.component';
import { EventoListComponent } from './evento-list/evento-list.component';

const routes: Routes = [
  {path: '', component: EventoListComponent },
  {path:'criacao', component: EventoCadastroComponent,data: {breadcrumb: 'criacao'} },
];


@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class EventoRoutingModule { }
