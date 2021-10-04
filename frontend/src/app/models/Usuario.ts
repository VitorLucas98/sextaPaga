import { SelectItem } from "./SelectItem";

export class Usuario {
    id?: number;
    nome: string;
    email: string;
    dataNascimento: string;
    cpf: string;
    telefone: string;
    status: boolean;
    cargo: SelectItem;
}

export class UsuarioListagem{
    id?: number;
    nome: string;
    email: string;
    status: boolean;
    cargo: SelectItem;
}