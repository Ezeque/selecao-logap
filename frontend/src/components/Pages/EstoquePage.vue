<script setup>
import AreaFiltros from  '../Estoque/AreaFiltros'
import AreaTabela from '../Estoque/AreaTabela.vue';
import HeaderEstoque from '../Estoque/HeaderEstoque.vue';
</script>

<template>
    <VRow>
        <VCol style="margin-left:6em" class="" cols="12" lg="5">
            <HeaderEstoque/>
        </VCol>

        <VCol cols="12" lg="6">
            <AreaFiltros/>
        </VCol>
    </VRow>
    <VRow class="d-flex justify-center">
        <VCol cols="11">
            <AreaTabela/>
        </VCol>
    </VRow>
</template>