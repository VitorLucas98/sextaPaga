import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cargo } from '../models/Cargo';


@Injectable({
  providedIn: 'root'
})
export class CargoService {

  constructor(private http: HttpClient) { }

  baseUrl = 'http://localhost:8080/api/cargos';

  buscarTodos(): Observable<Cargo[]> {
    return this.http.get<Cargo[]>(this.baseUrl);
  }

}
