import { UsuarioService } from './../../services/usuario.service';
import { Component, OnInit } from '@angular/core';
import { Usuario, UsuarioListagem } from 'src/app/models/Usuario';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CrudOperationEnum } from 'src/app/enums/Crud-Operation.enum';

@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.css']
})
export class UsuarioListComponent implements OnInit {

  usuarios: UsuarioListagem[] = [];
  usuarioBuscado: Usuario;
  public isDialogVisible: boolean = false;
  private modoCrud: CrudOperationEnum = CrudOperationEnum.CREATE; 

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
      this.showDialog(CrudOperationEnum.READ);
    })

  }

  public editar(usuarioId: number): void {
    this.service.buscarPorId(usuarioId).subscribe(res => {
      this.usuarioBuscado = res
      this.showDialog(CrudOperationEnum.UPDATE);
    })
  }

  public getModoCrud(): CrudOperationEnum {
    return this.modoCrud;
  }

  public showDialog(operacao: CrudOperationEnum): void {
    this.modoCrud = operacao;
    this.isDialogVisible = true;
  }
}
