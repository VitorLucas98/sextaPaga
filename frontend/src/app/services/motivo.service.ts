import { Motivo, MotivoListagem } from '../models/Motivo';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SelectItem } from '../models/SelectItem';
import { CrudOperationEnum } from '../enums/Crud-Operation.enum';

@Injectable({
  providedIn: 'root'
})
export class MotivoService {

  baseUrl = '/api/motivos';
  service: any;

  constructor(private http: HttpClient) { }
  
  buscarTodos(): Observable<MotivoListagem[]> {
    const url = `${this.baseUrl}`;
    return this.http.get<MotivoListagem[]>(url);
  }

  buscarTodosSelect(): Observable<SelectItem[]> {
    const url = `${this.baseUrl}/select`;
    return this.http.get<SelectItem[]>(url);
  }

  buscarPorId(id: any): Observable<Motivo> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Motivo>(url);
  }

  inserir(motivo : Motivo) : Observable<Motivo> {
    return this.http.post<Motivo>(this.baseUrl, motivo);
  }

  atualizar(motivo : Motivo, id : number) : Observable<Motivo> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.put<Motivo>(url, motivo);
  }

  
}