import { Categoria } from "./Categoria";
import { Fornecedor } from "./Fornecedor";

export class Produto {
    id: number;
    name: string;
    localizacao: string;
    valor: number;
    quantidade: number;
    categoria: Categoria;
    fornecedor: Fornecedor;

}
