<script setup>
import AreaFiltros from  '../Estoque/AreaFiltros'
import AreaTabela from '../Estoque/AreaTabela.vue';
import HeaderEstoque from '../Estoque/HeaderEstoque.vue';
</script>

<template>
    <VRow>
        <VCol cols="12" md="7">
            <HeaderEstoque/>
        </VCol>

        <VCol cols="12" md="5">
            <AreaFiltros/>
        </VCol>
    </VRow>
    <VRow class="d-flex justify-center">
        <VCol cols="11">
            <AreaTabela/>
        </VCol>
    </VRow>
</template>