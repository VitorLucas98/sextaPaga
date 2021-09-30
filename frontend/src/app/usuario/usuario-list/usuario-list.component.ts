import { UsuarioService } from './../../services/usuario.service';
import { Component, OnInit } from '@angular/core';
import { UsuarioListagem } from 'src/app/models/Usuario';

@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.css']
})
export class UsuarioListComponent implements OnInit {

  usuarios: UsuarioListagem[] = [];

  constructor(private service : UsuarioService) { }

  ngOnInit(): void {
    this.buscarTodos();
  }

  buscarTodos(): void{
    this.service.buscarTodos().subscribe( res => {
      this.usuarios = res;
      console.log(this.usuarios);
    })
  }

}
