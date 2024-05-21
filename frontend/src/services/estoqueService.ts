import { Ref, ref } from "vue"
import { Produto } from "../models/Produto"
import axios from "axios"
import { Fornecedor } from "../models/Fornecedor"
import { Categoria } from "../models/Categoria"

/* RECEBE TODOS OS PRODUTOS*/
export const produtos: Ref<Produto[]> = ref([])

export const fornecedores: Ref<Fornecedor[]> = ref([])

export const categorias: Ref<Categoria[]> = ref([])

export const mostrarNovoProduto: Ref<boolean> = ref(false)

const backend_address = import.meta.env.VITE_BACKEND_ADDRESS

/* RECUPERA TODOS OS PRODUTOS NO BANCO */
const todosProdutosRequest = async () => {
    let res = await axios.get(`${backend_address}/produtos/todos`)
    return res.data
}

/* RECUPERA TODOS OS FORNECEDORES NO BANCO */
const todosFornecedoresRequest = async () => {
    let res = await axios.get(`${backend_address}/fornecedor/todos`)
    return res.data
}

/* RECUPERA TODAS AS CATEGORIAS NO BANCO */
const todasCategoriasRequest = async () => {
    let res = await axios.get(`${backend_address}/categoria/todas`)
    return res.data
}

/* CRIA PRODUTO NO BANCO */
const criaProdutoRequest = async (produto: Produto) => {
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

/* DELETA PRODUTO DO BANCO */
const excluiProdutoRequest = async (id: number) => {
    await axios.delete(`${backend_address}/produtos/excluir`,
        {
            params: {id}
        }
    )
}

/* CRIA CATEGORIA NO BANCO */
const criaCategoriaRequest = async (categoria: Categoria) => {
    let res = await axios.post(`${backend_address}/categoria/criar`,
        {
            nome: categoria.nome
        }
    )
    return res.data
}

/* CRIA FORNECEDOR NO BANCO */
const criaFornecedorRequest = async (fornecedor: Fornecedor) => {
    let res = await axios.post(`${backend_address}/fornecedor/criar`,
        {
            nome: fornecedor.nome
        }
    )
    return res.data
}

/* REALIZA BUSCA DE PRODUTOS */
export const recuperaTodosProdutos = async () => {
    let res = await todosProdutosRequest()
    produtos.value = res
    await recuperarFornecedores()
    await recuperarCategorias()
}

/* CRIA PRODUTO */
export const criaProduto = async (produto: Produto) => {
    await criaProdutoRequest(produto)
}

/* EXCLUI PRODUTO */
export const excluiProduto = async (id: number) => {
    await excluiProdutoRequest(id)
    await recuperaTodosProdutos()
}

/* CRIA CATEGORIA */
export const criaCategoria = async (categoria: Categoria) => {
    await criaCategoriaRequest(categoria)
}

/* CRIA FORNECEDOR */
export const criaFornecedor = async (fornecedor: Fornecedor) => {
    await criaFornecedorRequest(fornecedor)
}

/* RECUPERA TODOS OS FORNECEDORES */
const recuperarFornecedores = async () => {
    fornecedores.value = await todosFornecedoresRequest()
}

/* RECUPERA TODAS AS CATEGORIAS */
const recuperarCategorias = async () => {
    categorias.value = await todasCategoriasRequest()
}
