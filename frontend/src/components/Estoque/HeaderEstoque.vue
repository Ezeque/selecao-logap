<script setup lang="ts">
import { Ref, onMounted, ref } from 'vue';
import { Produto } from '../../models/Produto';
import { produtosEmFalta, ordenacao, mostrarNovoProduto, baixarRelatorio, decrescente, mudarOrdem } from '../../services/estoqueService';

</script>

<template>
    <div>
            <VCol cols="12" lg="12">
                <h1>
                    Lista de Produtos
                </h1>
                <span>{{ produtosEmFalta.length }} Produtos em Falta</span>
            </VCol>
            <VCol cols="12">
                <VBtn width="auto" color="success" class="mb-5 mr-5" @click="mostrarNovoProduto = true">
                    Novo Produto
                </VBtn>
                <VBtn width="auto" @click="baixarRelatorio" color="warning" class="mb-5">
                    Gerar Relatório
                </VBtn>
            </VCol>

            <VCol cols="12" lg="10">
                <VSelect hide-details v-model="ordenacao" :items="['Data de Criação', 'Quantidade', 'Valor', 'Ordem Alfabética']">
                    <template #append>
                        <VTooltip location="top" text="Inverter Ordem">
                            <template v-slot:activator="{ props }">
                                <VBtn v-bind="props" color="#5C4BAA" @click="decrescente = !decrescente; mudarOrdem()">
                                    <span class="texto-botao-ordem">
                                        {{ decrescente ? "Maior" : "Menor" }}
                                        <VIcon class="texto-botao-ordem" icon="mdi-arrow-right" /> {{ decrescente ?
                                            "Menor"
                                        : "Maior" }}
                                    </span>
                                </VBtn>
                            </template>
                        </VTooltip>
                    </template>
                </VSelect>
            </VCol>
    </div>

</template>

<style scoped>
#layout-header {
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding-left: 6em;
}

h1 {
    font-size: 3em;
}

span {
    font-weight: bold;
    color: #AD1717;
}

.texto-botao-ordem{
    color: white
}
</style>