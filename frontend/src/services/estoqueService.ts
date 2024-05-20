import { Ref, ref } from "vue"
import { Produto } from "../models/Produto"
import axios from "axios"
import { Fornecedor } from "../models/Fornecedor"
import { Categoria } from "../models/Categoria"

/* RECEBE TODOS OS PRODUTOS*/
export const produtos:Ref<Produto[]> = ref([])

export const fornecedores:Ref<Fornecedor[]> = ref([])

export const categorias:Ref<Categoria[]> = ref([])

const backend_address = import.meta.env.VITE_BACKEND_ADDRESS

/* RECUPERA TODOS OS PRODUTOS NO BANCO */
const todosProdutosRequest = async () =>{
    let res = await axios.get(`${backend_address}/produtos/todos`)
    return res.data
} 

/* CRIA PRODUTO NO BANCO */
const criaProdutoRequest = async (produto: Produto) =>{
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

export const recuperaTodosProdutos = async () =>{
    let res  = await todosProdutosRequest()
    produtos.value = res
    extrairFornecedores()
    extrairCategorias()
}

export const criaProduto = async (produto:Produto) =>{
    await criaProdutoRequest(produto)
}

const extrairFornecedores = () => {
    const fornecedorMap = new Map<number, Fornecedor>()

    produtos.value.forEach((produto) => {
        if (produto.fornecedor && !fornecedorMap.has(produto.fornecedor.id)) {
            fornecedorMap.set(produto.fornecedor.id, produto.fornecedor)
        }
    })

    fornecedores.value = Array.from(fornecedorMap.values())
}

const extrairCategorias = () => {
    const categoriaMap = new Map<number, Fornecedor>()

    produtos.value.forEach((produto) => {
        if (produto.categoria && !categoriaMap.has(produto.categoria.id)) {
            categoriaMap.set(produto.categoria.id, produto.categoria)
        }
    })

    categorias.value = Array.from(categoriaMap.values())
}
