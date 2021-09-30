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

}
