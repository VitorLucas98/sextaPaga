import { Motivo } from "./Motivo";
import { Situacao } from "./Situacao";
import { Usuario } from "./Usuario";

export class Evento{
    id: number;
    nome: string;
    dataEvento: string;
    valor: number;
    motivo: Motivo; 
    situacao: Situacao;
    usuarios: Usuario[];
}