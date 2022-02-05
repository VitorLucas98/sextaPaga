import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SelectItem } from '../models/SelectItem';

@Injectable({
  providedIn: 'root'
})
export class SituacaoService {

  constructor(private http: HttpClient) { }

  baseUrl = 'http://localhost:8080/api/situacoes';

  buscarTodos(): Observable<SelectItem[]> {
    return this.http.get<SelectItem[]>(this.baseUrl);
  }
}
