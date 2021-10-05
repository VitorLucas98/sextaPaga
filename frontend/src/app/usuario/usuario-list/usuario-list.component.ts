import { UsuarioService } from './../../services/usuario.service';
import { Component, OnInit } from '@angular/core';
import { Usuario, UsuarioListagem } from 'src/app/models/Usuario';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.css']
})
export class UsuarioListComponent implements OnInit {

  usuarios: UsuarioListagem[] = [];
  usuarioBuscado: Usuario;
  formEdicao: boolean = false;
  formView: boolean = false;

  constructor(private service: UsuarioService, private router: Router) { }

  ngOnInit(): void {
    this.buscarTodos();
  }

  cadastrar(): void {
    this.router.navigateByUrl('usuarios/criar')
  }

  buscarTodos(): void {
    this.service.buscarTodos().subscribe(res => {
      this.usuarios = res;
    })
  }

  public visualizar(usuarioId: number): void {
    this.service.buscarPorId(usuarioId).subscribe(res => {
      this.usuarioBuscado = res
      this.formView = true;
    })

  }

  public editar(usuarioId: number): void {
    this.service.buscarPorId(usuarioId).subscribe(res => {
      this.usuarioBuscado = res
      this.formEdicao = true;
    })
  }
}
