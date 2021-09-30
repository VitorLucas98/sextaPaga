import { Cargo } from "./Cargo";

export class Usuario {
    id?: number;
    nome: string;
    email: string;
    dataNascimento: string;
    cpf: string;
    telefone: string;
    status: boolean;
    cargo: Cargo;
}

export class UsuarioListagem{
    id?: number;
    nome: string;
    email: string;
    status: boolean;
    cargo: Cargo;
}