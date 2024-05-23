<script setup>
import AreaFiltros from  '../Estoque/AreaFiltros'
import AreaTabela from '../Estoque/AreaTabela.vue';
import HeaderEstoque from '../Estoque/HeaderEstoque.vue';
import {computed} from 'vue'
import {useDisplay} from 'vuetify'

const isMobile = useDisplay().smAndDown
</script>

<template>
    <VRow>
        <VCol :order="isMobile ? 2 : 1" :style="!isMobile ? 'margin-left:6em' : ''" class="px-1" cols="12" lg="5">
            <HeaderEstoque/>
        </VCol>

        <VCol :order="isMobile ? 1 : 2" cols="12" lg="6">
            <AreaFiltros/>
        </VCol>
    </VRow>
    <VRow class="d-flex justify-start">
        <VCol sm="12" cols="12">
            <AreaTabela/>
        </VCol>
    </VRow>
</template>