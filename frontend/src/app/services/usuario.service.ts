import { Usuario } from './../models/Usuario';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  baseUrl = 'http://localhost:8080/api/usuarios';

  constructor(private http: HttpClient) { }

  buscarTodos(): Observable<Usuario[]> {
    const url = `${this.baseUrl}/filtro`;
    return this.http.get<Usuario[]>(url);
  }

  buscarPorId(id: any): Observable<Usuario> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Usuario>(url);
  }

  inserir(usuario : Usuario) : Observable<Usuario> {
    return this.http.post<Usuario>(this.baseUrl, usuario);
  }

  atualizacao(usuario : Usuario, id : any) : Observable<Usuario> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.put<Usuario>(url, usuario);
  }

  ativarUsuario(id : any) : Observable<Usuario> {
    const url = `${this.baseUrl}/ativar/${id}`;
    return this.http.put<Usuario>(url, null);
  }

  desativarUsuario(id : any) : Observable<Usuario>{
    const url = `${this.baseUrl}/desativar/${id}`;
    return this.http.put<Usuario>(url, null);
  }
}
