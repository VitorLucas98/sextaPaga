import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SelectItem } from '../models/SelectItem';


@Injectable({
  providedIn: 'root'
})
export class CargoService {

  constructor(private http: HttpClient) { }

  baseUrl = 'http://localhost:8080/api/cargos';

  buscarTodos(): Observable<SelectItem[]> {
    return this.http.get<SelectItem[]>(this.baseUrl);
  }

}
