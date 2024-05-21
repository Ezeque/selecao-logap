import { Ref, ref } from "vue"
import { Produto } from "../models/Produto"
import { Fornecedor } from "../models/Fornecedor"
import { Categoria } from "../models/Categoria"
import { criaCategoriaRequest, criaFornecedorRequest, criaProdutoRequest, excluiProdutoRequest, todasCategoriasRequest, todosFornecedoresRequest, todosProdutosRequest } from "../api/estoque"

/* RECEBE TODOS OS PRODUTOS*/
export const produtos: Ref<Produto[]> = ref([])

export const fornecedores: Ref<Fornecedor[]> = ref([])

export const categorias: Ref<Categoria[]> = ref([])

export const mostrarNovoProduto: Ref<boolean> = ref(false)

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
