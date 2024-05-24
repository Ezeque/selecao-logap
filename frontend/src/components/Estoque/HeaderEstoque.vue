<script setup lang="ts">
import { Ref, onMounted, ref } from 'vue';
import { useDisplay } from 'vuetify'
import { Produto } from '../../models/Produto';
import { loadingRelatorio, produtosEmFalta, ordenacao, mostrarCriacao, baixarRelatorio, decrescente, mudarOrdem } from '../../services/estoqueService';
import { VProgressCircular } from 'vuetify/components';

const isMobile = useDisplay().smAndDown

</script>

<template>
    <div>
        <VCol class="d-md-block d-none" lg="12">
            <h1>
                Lista de Produtos
            </h1>
            <span>{{ produtosEmFalta.length }} Produtos em Falta</span>
        </VCol>
        <VCol class="d-flex justify-sm-start justify-center p-0" cols="12">
            <VBtn :width="isMobile ? '45%' : ''" color="success" class="mr-3" @click="mostrarCriacao = true">
                Criar
            </VBtn>
            <VBtn :width="isMobile ? '45%' : ''" @click="baixarRelatorio" color="warning" class="mb-5">
                <span class="texto-botao" v-if="!loadingRelatorio">
                    Gerar Relatório
                </span>
                <VProgressCircular indeterminate v-else/>
            </VBtn>
        </VCol>


        <VCol cols="12" lg="10">
            <VSelect hide-details v-model="ordenacao"
                :items="['Data de Criação', 'Quantidade', 'Valor', 'Ordem Alfabética']">
                <template #append>
                    <VTooltip location="top" text="Inverter Ordem">
                        <template v-slot:activator="{ props }">
                            <VBtn v-bind="props" color="#5C4BAA" @click="decrescente = !decrescente; mudarOrdem()">
                                <span class="texto-botao">
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

.texto-botao {
    color: white
}
</style>