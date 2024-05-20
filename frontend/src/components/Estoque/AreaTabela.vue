<script setup lang="ts">
import { Ref, ref, onMounted } from 'vue';
import { produtos, recuperaTodosProdutos, fornecedores, categorias, criaProduto } from '../../services/estoqueService';
import { VAutocomplete, VProgressCircular, VSpacer, VTextField } from 'vuetify/components';
import { Produto } from '../../models/Produto';
import { Fornecedor } from '../../models/Fornecedor';
import { Categoria } from '../../models/Categoria';

const loading: Ref<boolean> = ref(true)
const erro: Ref<boolean> = ref(false)
const mostrarNovoProduto: Ref<boolean> = ref(false)

const novoProduto:Ref<Produto> = ref(new Produto())

const salvarProduto = async () =>{
    mostrarNovoProduto.value = false
    await criaProduto(novoProduto.value)
    await recuperaTodosProdutos()
}

onMounted(() => {
    try {
        recuperaTodosProdutos()
    }
    catch (e) {
        erro.value = true
    }
    loading.value = false
})
</script>

<template>
    
    <div id="layout">
        <VBtn color="success" class="mb-5 mr-5" @click="mostrarNovoProduto = true">
            Novo Produto
        </VBtn>
        <VBtn color="warning" class="mb-5">
            Gerar Relatório
        </VBtn>
        <div id="layout-tabela">
            <VTable id="tabela" fixed-header>
                <thead>
                    <th>
                        Nome do Produto
                    </th>
                    <th>
                        Fornecedor
                    </th>
                    <th>
                        Categoria
                    </th>
                    <th>
                        Valor
                    </th>
                    <th>
                        Quantidade
                    </th>
                    <th>
                        Localização
                    </th>
                </thead>
                <tbody>
                    <tr v-if="mostrarNovoProduto && !loading && !erro">
                        <td>
                            <VTextField label="Nome" placeholder="Nome do Produto" hide-details />
                        </td>
                        <td>
                            <VTextField label="Fornecedor" placeholder="Fornecedor do Produto" hide-details />
                        </td>
                        <td>
                            <VTextField label="Categoria" placeholder="Categoria do Produto" hide-details />
                        </td>
                        <td>
                            <VTextField label="valor" placeholder="Valor do Produto" hide-details />
                        </td>
                        <td>
                            <VTextField label="Quantidade" placeholder="Quantidade do Produto" hide-details />
                        </td>
                        <td class="p-0">
                            <VTextField label="Localização" placeholder="Localização do Produto" hide-details />
                        </td>

                    </tr>
                    <!-- CASO ESTEJA CARREGANDO  -->

                    <tr v-if="loading">
                        <td colspan="6" class="text-center py-5">
                            <VProgressCircular class="text-center" indeterminate />
                        </td>
                    </tr>

                    <!-- CASO TENHA OCORRIDO UM ERRO -->

                    <tr v-if="erro">
                        <td colspan="6" class="text-center py-5">
                            <h2>Ocorreu um Erro ao Recuperar os Produtos</h2>
                        </td>
                    </tr>

                    <!-- TABELA COM PRODUTOS -->

                    <tr v-if="!loading && !erro" v-for="(produto, index) in produtos" :key="`produto-${index}`">
                        <td>
                            {{ produto.name }}
                        </td>
                        <td>
                            {{ produto.fornecedor.nome }}
                        </td>
                        <td>
                            {{ produto.categoria.nome }}
                        </td>
                        <td>
                            {{ produto.valor }}
                        </td>
                        <td>
                            {{ produto.quantidade }}
                        </td>
                        <td>
                            {{ produto.localizacao }}
                        </td>
                    </tr>
                </tbody>
            </VTable>
        </div>

        <!-- DIALOG DE CRIAÇÃO DE PRODUTO -->

        <VDialog fullscreen v-model="mostrarNovoProduto">
            <VCard>
                <VToolbar class="toolbar text-white mb-5">
                    <VBtn icon="mdi-close" @click="mostrarNovoProduto = false"></VBtn>

                    <VToolbarTitle>Cadastrar Novo Produto</VToolbarTitle>

                    <VSpacer></VSpacer>

                    <VToolbarItems>
                        <VBtn class="" @click="salvarProduto">Salvar Produto</VBtn>
                    </VToolbarItems>
                </VToolbar>
                <VForm>
                    <VRow>
                        <VCol>
                            <VTextField label="Nome" placeholder="Nome do Produto" hide-details v-model="novoProduto.name" />
                        </VCol>
                    </VRow>
                    <VRow>
                        <VCol md="6">
                            <VAutocomplete label="Categoria" placeholder="Categoria do Produto" return-object :items="categorias" item-title="nome"
                                v-model="novoProduto.categoria" />
                        </VCol>
                        <VCol md="6">
                            <VAutocomplete label="Fornecedor" placeholder="Fornecedor do Produto" return-object :items="fornecedores" item-title="nome"
                                v-model="novoProduto.fornecedor" />
                        </VCol>
                    </VRow>
                    <VRow>
                        <VCol md="6">
                            <VTextField label="valor" placeholder="Valor do Produto" hide-details v-model="novoProduto.valor" />
                        </VCol>
                        <VCol md="6">
                            <VTextField label="Quantidade" placeholder="Quantidade do Produto" hide-details v-model="novoProduto.quantidade" />
                        </VCol>
                    </VRow>
                    <VRow>
                        <VTextField label="Localização" placeholder="Localização do Produto" hide-details v-model="novoProduto.localizacao" />
                    </VRow>
                </VForm>
            </VCard>
        </VDialog>

    </div>
</template>

<style scoped>
.info {
    color: #675656;
}

#layout-tabela {
    border: 1px solid black;
    border-radius: 10px;
}

#layout {
    margin-left: 8em;
    padding: 0.5em;
}

th {
    text-align: left;
    border-bottom: 1px solid black;
    padding: 1em;
    background-color: white;
}

#tabela {
    max-height: 28em;
}

.toolbar {
    background-color: #5C4BAA;
}
</style>