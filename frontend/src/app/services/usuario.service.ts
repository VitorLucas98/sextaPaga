import { Usuario, UsuarioListagem } from './../models/Usuario';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SelectItem } from '../models/SelectItem';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  baseUrl = '/api/usuarios';

  constructor(private http: HttpClient) { }

  buscarTodos(): Observable<UsuarioListagem[]> {
    const url = `${this.baseUrl}/filtro`;
    return this.http.get<UsuarioListagem[]>(url);
  }

  buscarTodosSelect(): Observable<SelectItem[]>{
    const url = `${this.baseUrl}/select`;
    return this.http.get<SelectItem[]>(url);
  }

  buscarPorId(id: any): Observable<Usuario> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Usuario>(url);
  }

  inserir(usuario : Usuario) : Observable<Usuario> {
    return this.http.post<Usuario>(this.baseUrl, usuario);
  }

  atualizar(usuario : Usuario, id : number) : Observable<Usuario> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.put<Usuario>(url, usuario);
  }

  ativarUsuario(id : number) : Observable<Usuario> {
    const url = `${this.baseUrl}/ativar/${id}`;
    return this.http.put<Usuario>(url, null);
  }

  desativarUsuario(id : number) : Observable<Usuario>{
    const url = `${this.baseUrl}/desativar/${id}`;
    return this.http.put<Usuario>(url, null);
  }
}
