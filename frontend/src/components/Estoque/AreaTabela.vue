<script setup lang="ts">
import { Ref, ref, onMounted } from 'vue';
import CriarProduto from './CriarProduto.vue';
import { erro, loading, produtosFiltrados, recuperaTodosProdutos, mostrarNovoProduto, excluiProduto } from '../../services/estoqueService';
import { VProgressCircular } from 'vuetify/components';
import { Produto } from '../../models/Produto';
import LinhaProduto from '../Utils/LinhaProduto.vue';

/* GUARDA A INSTÂNCIA DO PRODUTO A SER EXCLUÍDO */
const produtoExcluir: Ref<Produto | null> = ref(null)

/* GUARDA SE DEVE SER EXIBIDO O DIALOG PARA EXCLUIR PROPDUTO */
const mostrarDialogoExcluirProduto: Ref<boolean> = ref(false)

onMounted(async () => {
    try {
        await recuperaTodosProdutos()
    }
    catch (e) {
        erro.value = true
    }
    loading.value = false
})

/* REALIZA DELEÇÃO DE PRODUTO */
const deletaProduto = async (id: number) => {
    loading.value = true;
    try {
        await excluiProduto(id)
        loading.value = false
    }
    catch (e) {
        erro.value = true
        loading.value = false
    }
}
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
                    <th>
                        Ações
                    </th>
                </thead>
                <tbody>
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

                    <LinhaProduto v-for="(produto, index) in produtosFiltrados" :produto="produto"/>
                </tbody>
            </VTable>
        </div>

        <!-- DIALOG DE CRIAÇÃO DE PRODUTO -->

        <VDialog fullscreen v-model="mostrarNovoProduto">
            <CriarProduto />
        </VDialog>

        <!-- DIALOG DE CRIAÇÃO DE PRODUTO -->

        <VDialog class="p-5" width="600" v-model="mostrarDialogoExcluirProduto">
            <VCard class="text-center p-5">
                <VCardTitle>
                    Tem certeza de que deseja excluir o seguinte produto?
                </VCardTitle>
                <VCardSubtitle class="font-weight-bold text-xl">
                    Nome: {{produtoExcluir.name}} |
                    Fornecedor: {{ produtoExcluir.fornecedor.nome }}
                </VCardSubtitle>
                <VCardText>
                    <VRow>
                        <VCol>
                            <VBtn width="100%"
                                @click="deletaProduto(produtoExcluir.id); mostrarDialogoExcluirProduto = false"
                                color="error">
                                Confirmar
                            </VBtn>
                        </VCol>
                        <VCol>
                            <VBtn width="100%" @click="mostrarDialogoExcluirProduto = false" color="success">
                                Cancelar
                            </VBtn>
                        </VCol>
                    </VRow>

                </VCardText>
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
</style>