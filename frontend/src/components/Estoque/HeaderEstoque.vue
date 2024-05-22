<script setup lang="ts">
import { Ref, onMounted, ref } from 'vue';
import { Produto } from '../../models/Produto';
import { produtosEmFalta } from '../../services/estoqueService';

</script>

<template>
    <div id="layout-header">
        <h1>
            Lista de Produtos
        </h1>
        <span>{{produtosEmFalta.length}} Produtos em Falta</span>
    </div>
</template>

<style scoped>
#layout-header{
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding-left: 6em;
}
h1{
    font-size: 3em;
}
span{
    font-weight: bold;
    color: #AD1717;
}
</style>