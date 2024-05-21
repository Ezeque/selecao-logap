import { Ref, ref } from "vue"
import { Produto } from "../models/Produto"
import { Fornecedor } from "../models/Fornecedor"
import { Categoria } from "../models/Categoria"
import { ProdutosFaltaRequest, criaCategoriaRequest, criaFornecedorRequest, criaProdutoRequest, excluiProdutoRequest, geraRelatorio, todasCategoriasRequest, todosFornecedoresRequest, todosProdutosRequest } from "../api/estoque"

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

/* GUARDA SE TELA ESTÁ CARREGANDO */
export const loading: Ref<boolean> = ref(true)

/* GUARDA SE OCORREU ERRO */
export const erro: Ref<boolean> = ref(false)

/* GUARDA SE DEVE SER EXIBIDO O DIALOG PARA EXCLUIR PROPDUTO */
export const mostrarDialogoExcluirProduto: Ref<boolean> = ref(false)

/* GUARDA A INSTÂNCIA DO PRODUTO A SER EXCLUÍDO */
export const produtoExcluir: Ref<Produto | null> = ref(null)

/* REALIZA BUSCA DE PRODUTOS */
export const recuperaTodosProdutos = async () => {
    let res = await todosProdutosRequest()
    produtos.value = res
    aplicarFiltros()
    await recuperarFornecedores()
    await recuperarCategorias()
}

/* REALIZA BUSCA DE PRODUTOS EM FALTA*/
export const recuperaProdutosFalta = async () => {
    let res: Produto[] = await ProdutosFaltaRequest()
    return res
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

/* GERA E REALIZA DOWNLOAD DO RELATÓRIO */
export const baixarRelatorio = async () =>{
    let res = await geraRelatorio()
    const blob = new Blob([res], {type: "application/pdf"})
    const linkRelatorio = document.createElement("a")
    linkRelatorio.href = window.URL.createObjectURL(blob)
    linkRelatorio.download = `Relatório`
    document.body.appendChild(linkRelatorio)
    linkRelatorio.click()
    document.body.removeChild(linkRelatorio)
    window.URL.revokeObjectURL(linkRelatorio.href)
}
