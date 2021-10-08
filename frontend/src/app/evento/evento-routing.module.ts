import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EventoCadastroComponent } from './evento-cadastro/evento-cadastro.component';
import { EventoListComponent } from './evento-list/evento-list.component';

const routes: Routes = [
  {path: '', component: EventoListComponent },
  {path:'criar', component: EventoCadastroComponent,data: {breadcrumb: 'criar'} },
];


@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class EventoRoutingModule { }
