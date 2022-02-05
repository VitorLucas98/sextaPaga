import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { LoginSuccessComponent } from '@nuvem/angular-base';
import { HomeComponent } from './components/home/home.component';


const routes: Routes = [

    { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros'} },
    { path: 'login-success', component: LoginSuccessComponent },
    { path: '', component: HomeComponent},
    { path: 'usuarios', loadChildren: './usuario/usuario.module#UsuarioModule', data: { breadcrumb: 'usuarios'}},
    { path: 'motivos', loadChildren: './motivo/motivo.module#MotivoModule', data: { breadcrumb: 'motivos'}},
    { path: 'eventos', loadChildren: './evento/evento.module#EventoModule', data: { breadcrumb: 'eventos'}}
    
];


@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }


