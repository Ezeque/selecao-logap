<script setup lang="ts">
import { Ref, ref } from 'vue';
import { Produto } from '../../models/Produto';
import { VAutocomplete, VTextField } from 'vuetify/components';
import { produtoExcluir, mostrarDialogoExcluirProduto, erro, loading, fornecedores, categorias, criaCategoria, recuperaTodosProdutos, criaFornecedor, criaProduto } from '../../services/estoqueService';
import { Categoria } from '../../models/Categoria';

const props = defineProps({
    produto: Produto
})

const editar: Ref<boolean> = ref(false)
const novoProduto: Ref<Produto> = ref(props.produto)
const categoriaAutocomplete: Ref<any> = ref()
const fornecedorAutocomplete: Ref<any> = ref()

const edicao = async (produto: Produto) => {
    loading.value = true
    try {
        /* ATUALIZARÁ O PRODUTO SALVO NO BANCO */
        await criaProduto(produto)
        editar.value = !(editar.value)
    } catch (e) {
        loading.value = false
        erro.value = true
    }
    loading.value = false
    erro.value = false

}

const salvarCategoria = async (nomeCategoria: string) => {
    let novaCategoria = new Categoria()
    novaCategoria.nome = nomeCategoria
    await criaCategoria(novaCategoria)
    await recuperaTodosProdutos()
}

const salvarFornecedor = async (nomeFornecedor: string) => {
    let novoFornecedor = new Categoria()
    novoFornecedor.nome = nomeFornecedor
    await criaFornecedor(novoFornecedor)
    await recuperaTodosProdutos()
}
</script>

<template>
    <tr :class="produto.quantidade == 0 ? 'text-error font-weight-bold' : ''">
        <!-- CASO O PRODUTO NÃO ESTEJA SENDO EDITADO -->
        <td v-if="!editar">
            {{ produto.name }}
        </td>
        <td v-if="!editar">
            {{ produto.fornecedor.nome }}
        </td>
        <td v-if="!editar">
            {{ produto.categoria.nome }}
        </td>
        <td v-if="!editar">
            {{ produto.valor }}
        </td>
        <td v-if="!editar">
            {{ produto.quantidade }}
        </td>
        <td v-if="!editar">
            {{ produto.localizacao }}
        </td>

        <!-- CASO O PRODUTO ESTEJA SENDO EDITADO -->
        <td v-if="editar">
            <VTextField placeholder="Nome do Produto" label="Nome" v-model="novoProduto.name" hide-details="auto" />
        </td>

        <td v-if="editar">
            <VAutocomplete ref="fornecedorAutocomplete" item-title="nome" return-object :items="fornecedores"
                placeholder="Fornecedor do Produto" label="Fornecedor" v-model="novoProduto.fornecedor"
                hide-details="auto">
                <template #append-item>
                    <VBtn @click="salvarFornecedor(fornecedorAutocomplete?.modelValue)" color="success"
                        v-if="fornecedorAutocomplete?.filteredItems.length == 0 && fornecedorAutocomplete.modelValue != ''">
                        Criar fornecedor {{ fornecedorAutocomplete?.modelValue }}
                    </VBtn>
                </template>
            </VAutocomplete>
        </td>

        <td v-if="editar">
            <VAutocomplete ref="categoriaAutocomplete" item-title="nome" return-object :items="categorias"
                placeholder="Categoria do Produto" label="Categoria" v-model="novoProduto.categoria"
                hide-details="auto">
                <template #append-item>
                    <VBtn @click="salvarCategoria(categoriaAutocomplete?.modelValue)" color="success"
                        v-if="categoriaAutocomplete?.filteredItems.length == 0 && categoriaAutocomplete.modelValue != ''">
                        Criar Categoria {{ categoriaAutocomplete?.modelValue }}
                    </VBtn>
                </template>
            </VAutocomplete>
        </td>

        <td v-if="editar">
            <VTextField placeholder="Valor do Produto" label="Valor" v-model="novoProduto.valor" hide-details="auto" />
        </td>

        <td v-if="editar">
            <VTextField placeholder="Quantidade do Produto" label="Quantidade" v-model="novoProduto.quantidade"
                hide-details="auto" />
        </td>

        <td v-if="editar">
            <VTextField placeholder="Localização do Produto" label="Localização" v-model="novoProduto.localizacao"
                hide-details="auto" />
        </td>

        <td>
            <VTooltip location="top" :text="editar ? 'Cancelar' : 'Excluir'">
                <template v-slot:activator="{ props }">
                    <VBtn class="mr-1"
                        @click="editar ? editar = false : mostrarDialogoExcluirProduto = true; produtoExcluir = produto"
                        v-bind="props" color="error" size="30" :icon="editar ? 'mdi-close' : 'mdi-trash-can-outline'" />
                </template>
            </VTooltip>
            <VTooltip location="top" :text="editar ? 'Confirmar' : 'Editar'">
                <template v-slot:activator="{ props }">
                    <VBtn @click="editar? edicao(novoProduto) : editar = true" v-bind="props" color="success" size="30"
                        :icon="editar ? 'mdi-check' : 'mdi-pencil'" />
                </template>
            </VTooltip>
        </td>
    </tr>
</template>