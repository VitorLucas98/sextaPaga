import { MotivoService } from './../../services/motivo.service';
import { Component, OnInit } from '@angular/core';
import { Motivo } from 'src/app/models/Motivo';
import { MessageService } from 'primeng';
import { Router } from '@angular/router';

@Component({
  selector: 'app-motivo-list',
  templateUrl: './motivo-list.component.html',
  styleUrls: ['./motivo-list.component.css']
})
export class MotivoListComponent implements OnInit {

  motivos: Motivo[] = [];
  

  constructor(private service : MotivoService, private router: Router,
    private messageService: MessageService) { }

  /*constructor(private service : MotivoService, private router: Router, private messageService: MessageService) { }
*/
  ngOnInit(): void {
    this.buscarTodos();
  }

  cadastrar(): void{
    this.router.navigateByUrl('motivos/criacao')
  }

  buscarTodos(): void{
    this.service.buscarTodos().subscribe( res => {
      this.motivos = res;
      console.log(this.motivos);
    })
  }

}
