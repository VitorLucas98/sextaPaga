import { UsuarioService } from './../../services/usuario.service';
import { Component, OnInit } from '@angular/core';
import { UsuarioListagem } from 'src/app/models/Usuario';
import { Router } from '@angular/router';

@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.css']
})
export class UsuarioListComponent implements OnInit {

  usuarios: UsuarioListagem[] = [];

  constructor(private service : UsuarioService, private router: Router) { }

  ngOnInit(): void {
    this.buscarTodos();
  }

  cadastrar(): void{
    this.router.navigateByUrl('usuarios/criar')
  }

  buscarTodos(): void{
    this.service.buscarTodos().subscribe( res => {
      this.usuarios = res;
    })
  }

}
