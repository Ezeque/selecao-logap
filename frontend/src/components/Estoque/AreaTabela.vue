<script setup lang="ts">
import { Ref, ref, onMounted } from 'vue';
import { produtos, recuperaTodosProdutos } from '../../services/estoqueService';
import { VProgressCircular, VTextField } from 'vuetify/components';

const loading: Ref<boolean> = ref(true)
const erro: Ref<boolean> = ref(false)

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
        <VBtn color="success" class="mb-5 mr-5">
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
    </div>
</template>

<style scoped>
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