import { Ref, ref } from "vue"
import { Produto } from "../models/Produto"
import { Fornecedor } from "../models/Fornecedor"
import { Categoria } from "../models/Categoria"
import { criaCategoriaRequest, criaFornecedorRequest, criaProdutoRequest, excluiProdutoRequest, todasCategoriasRequest, todosFornecedoresRequest, todosProdutosRequest } from "../api/estoque"

/* RECEBE TODOS OS PRODUTOS*/
export const produtos: Ref<Produto[]> = ref([])

export const produtosFiltrados: Ref<Produto[]> = ref([])

export const fornecedores: Ref<Fornecedor[]> = ref([])

export const categorias: Ref<Categoria[]> = ref([])

export const mostrarNovoProduto: Ref<boolean> = ref(false)

export const filtroNome: Ref<string|null> = ref(null)

export const filtroCategoria: Ref<string|null> = ref(null)

export const filtroFornecedor: Ref<string|null> = ref(null)

export const mostrarEsgotados: Ref<boolean> = ref(true)

/* REALIZA BUSCA DE PRODUTOS */
export const recuperaTodosProdutos = async () => {
    let res = await todosProdutosRequest()
    produtos.value = res
    aplicarFiltros()
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

/* APLICA OS FILTROS */
export const aplicarFiltros = async () => {
    produtosFiltrados.value = produtos.value
    if(filtroNome.value){
        produtosFiltrados.value = produtosFiltrados.value.filter(produto => produto.name.indexOf(filtroNome.value!) > -1)
    }
    if(filtroCategoria!.value){
        produtosFiltrados.value = produtosFiltrados.value.filter(produto => produto.categoria.nome.indexOf(filtroCategoria.value!) > -1)
    }
    if(filtroFornecedor.value){
        produtosFiltrados.value = produtosFiltrados.value.filter(produto => produto.fornecedor.nome.indexOf(filtroFornecedor.value!) > -1)
    }
    if(!mostrarEsgotados.value){
        produtosFiltrados.value = produtosFiltrados.value.filter(produto => produto.quantidade > 0)
    }
}
