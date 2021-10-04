import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventoRoutingModule } from './evento-routing.module';
import { EventoCadastroComponent } from './evento-cadastro/evento-cadastro.component';
import { EventoListComponent } from './evento-list/evento-list.component';


@NgModule({
  declarations: [ EventoListComponent, EventoCadastroComponent],
  imports: [
    CommonModule,
    EventoRoutingModule,
    SharedModule
  ]
})
export class EventoModule { }
