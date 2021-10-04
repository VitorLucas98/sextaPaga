import { EventoService } from './../../services/evento.service';
import { Component, OnInit } from '@angular/core';
import { EventoListagem } from '../../models/Evento';
import { Router } from '@angular/router';

@Component({
  selector: 'app-evento-list',
  templateUrl: './evento-list.component.html',
  styleUrls: ['./evento-list.component.css']
})
export class EventoListComponent implements OnInit {

  eventos: EventoListagem[] = [];


  constructor(private service : EventoService, private router: Router) { }

  ngOnInit(): void {
    this.buscarTodos();
  }

  cadastrar(): void{
    this.router.navigateByUrl('eventos/criacao')
  }

  buscarTodos(): void{
    this.service.buscarTodos().subscribe( res => {
      this.eventos = res;
      console.log(this.eventos);
    })
  }

}
