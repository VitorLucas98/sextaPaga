import { SelectItem } from "./SelectItem";


export class Evento{
    id: any;
    nome: string;
    dataEvento: string;
    valor: number;
    motivo: SelectItem; 
    situacao: SelectItem;
    usuarios: SelectItem[];
}

export class EventoListagem{
    id: any;
    nome: string;
    dataEvento: string;
    valor: number;
    motivo: SelectItem; 
    situacao: SelectItem;
    usuarios: SelectItem[];
}