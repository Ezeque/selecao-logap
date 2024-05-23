<script lang="ts" setup>
import { ref, Ref } from 'vue';
import { categorias, fornecedores, filtroNome, filtroCategoria, filtroFornecedor, mostrarEsgotados, aplicarFiltros } from '../../services/estoqueService';

const form:Ref<any> = ref()

const limparFiltros = () => {
    filtroNome.value = null
    filtroCategoria.value = null
    filtroFornecedor.value = null
    mostrarEsgotados.value = true
}



</script>

<!-- FORMULÁRIO DE FILTRAGEM DE PRODUTOS -->

<template>
    <VForm ref="form">
        <VRow>
            <VCol md="11" cols="12">
                <VRow>
                    <VCol cols="12" class="pb-0 mt-1">
                        <VTextField v-model="filtroNome" label="Produto:" placeholder="Nome do Produto"></VTextField>
                    </VCol>
                </VRow>
                <VRow>
                    <VCol cols="6" class="py-0">
                        <VAutocomplete no-data-text="Nenhum Resultado" :items="categorias" item-title="nome" item-value="nome" v-model="filtroCategoria" label="Categoria:" placeholder="Nome da Categoria"></VAutocomplete>
                    </VCol>
                    <VCol cols="6" class="py-0">
                        <VAutocomplete no-data-text="Nenhum Resultado" :items="fornecedores" item-title="nome" item-value="nome" v-model="filtroFornecedor" label="Fornecedor:" placeholder="Nome do Fornecedor"></VAutocomplete>
                    </VCol>
                </VRow>
                <VRow>
                    <VCol cols="12" class="d-flex justify-end py-0">
                        <VCheckbox v-model="mostrarEsgotados" label="Mostrar Produtos Esgotados" />
                    </VCol>
                </VRow>
                <VRow>
                    <VCol cols="12" class="d-flex justify-center py-0">
                        <VBtn @click="aplicarFiltros" color="#5C4BAA" width="50%" class="mr-3">Filtrar Resultados</VBtn>
                        <VBtn @click="limparFiltros" color="error" width="50%">Limpar Filtros</VBtn>
                    </VCol>
                </VRow>
            </VCol>
        </VRow>
    </VForm>
</template>

<style scoped></style>