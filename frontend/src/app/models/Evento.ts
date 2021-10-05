import { Motivo } from "./Motivo";
import { SelectItem } from "./SelectItem";
import { Usuario } from "./Usuario";

export class Evento{
    id: number;
    nome: string;
    dataEvento: string;
    valor: number;
    motivo: Motivo; 
    situacao: SelectItem;
    usuarios: Usuario[];
}

export class EventoListagem{
    id: number;
    nome: string;
    dataEvento: string;
    valor: number;
    motivo: Motivo; 
    situacao: SelectItem;
    usuarios: Usuario[];
}