import { Motivo, MotivoListagem } from '../models/Motivo';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MotivoService {

  baseUrl = 'http://localhost:8080/api/motivos';

  constructor(private http: HttpClient) { }
  
  buscarTodos(): Observable<MotivoListagem[]> {
    const url = `${this.baseUrl}`;
    return this.http.get<MotivoListagem[]>(url);
  }

  buscarPorId(id: any): Observable<Motivo> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Motivo>(url);
  }

  inserir(motivo : Motivo) : Observable<Motivo> {
    return this.http.post<Motivo>(this.baseUrl, motivo);
  }

  atualizacao(motivo : Motivo, id : any) : Observable<Motivo> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.put<Motivo>(url, motivo);
  }
  
}