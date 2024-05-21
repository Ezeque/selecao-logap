import axios from "axios"
import { Produto } from "../models/Produto"
import { Categoria } from "../models/Categoria"
import { Fornecedor } from "../models/Fornecedor"

const backend_address = import.meta.env.VITE_BACKEND_ADDRESS


/* RECUPERA TODOS OS PRODUTOS NO BANCO */
export const todosProdutosRequest = async () => {
    let res = await axios.get(`${backend_address}/produtos/todos`)
    return res.data
}

/* RECUPERA PRODUTOS EM FALTA NO BANCO */
export const ProdutosFaltaRequest = async () => {
    let res = await axios.get(`${backend_address}/produtos/falta`)
    return res.data
}

/* RECUPERA TODOS OS FORNECEDORES NO BANCO */
export const todosFornecedoresRequest = async () => {
    let res = await axios.get(`${backend_address}/fornecedor/todos`)
    return res.data
}

/* RECUPERA TODAS AS CATEGORIAS NO BANCO */
export const todasCategoriasRequest = async () => {
    let res = await axios.get(`${backend_address}/categoria/todas`)
    return res.data
}

/* CRIA PRODUTO NO BANCO */
export const criaProdutoRequest = async (produto: Produto) => {
    let res = await axios.post(`${backend_address}/produtos/salvar`,
        {
            name: produto.name,
            localizacao: produto.localizacao,
            valor: produto.valor,
            quantidade: produto.quantidade,
            categoria: produto.categoria,
            fornecedor: produto.fornecedor
        }
    )
    return res.data
}

/* ATUALIZA PRODUTO NO BANCO */
export const atualizaProdutoRequest = async (produto: Produto) => {
    let res = await axios.post(`${backend_address}/produtos/salvar`,
        {
            id: produto.id,
            name: produto.name,
            localizacao: produto.localizacao,
            valor: produto.valor,
            quantidade: produto.quantidade,
            categoria: produto.categoria,
            fornecedor: produto.fornecedor
        }
    )
    return res.data
}

/* DELETA PRODUTO DO BANCO */
export const excluiProdutoRequest = async (id: number) => {
    await axios.delete(`${backend_address}/produtos/excluir`,
        {
            params: {id}
        }
    )
}

/* CRIA CATEGORIA NO BANCO */
export const criaCategoriaRequest = async (categoria: Categoria) => {
    let res = await axios.post(`${backend_address}/categoria/criar`,
        {
            nome: categoria.nome
        }
    )
    return res.data
}

/* CRIA FORNECEDOR NO BANCO */
export const criaFornecedorRequest = async (fornecedor: Fornecedor) => {
    let res = await axios.post(`${backend_address}/fornecedor/criar`,
        {
            nome: fornecedor.nome
        }
    )
    return res.data
}

/* GERA RELATÓRIO */
export const geraRelatorio = async () => {
    let res = await axios.get(`${backend_address}/relatorio/criar`,{
        responseType: 'arraybuffer'
    })
    return res.data
}