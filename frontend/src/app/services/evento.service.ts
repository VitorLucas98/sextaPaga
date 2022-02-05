import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Evento } from '../models/Evento';

@Injectable({
  providedIn: 'root'
})
export class EventoService {

  constructor(private http: HttpClient) { }

  baseUrl = 'http://localhost:8080/api/eventos';

  buscarTodos(): Observable<Evento[]> {
    const url = `${this.baseUrl}/filtro`;
    return this.http.get<Evento[]>(url);
  }
  
  buscarPorId( id : any): Observable<Evento> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Evento>(url);
  }

  inserir(evento : Evento): Observable<Evento>{
    return this.http.post<Evento>(this.baseUrl, evento);
  }
  
  atualizar(evento: Evento, id : number) : Observable<Evento> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.put<Evento>(url, evento);
  }

  deletar( id : number): Observable<void>{
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete<void>(url);
  }

  adiarEvento( id : number): Observable<void>{
    const url = `${this.baseUrl}/adiar/${id}`;
    return this.http.put<void>(url, null);
  }

}
