import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.css']
})
export class UsuarioListComponent implements OnInit {

  public cars: Car[] = [
    new Car('1', 2020, 'Fiat', 'Red'),
    new Car('2', 2020, 'Ford', 'Blue'),
    new Car('3', 2021, 'Fiat', 'Red'),
  ];

  constructor() { }

  ngOnInit(): void {
  }

}

class Car {
  constructor(
    public vin: string,
    public year: number,
    public brand: string,
    public color: string
  ) {}
}
