<script setup>
import SelecaoModulo from '../Utils/SelecaoModulo.vue';
</script>

<template>
    <div id="page">
        <SelecaoModulo class="mr-10" icon="mdi-text-box-search-outline" text="Encontrar Vogal" route="/vogal"/>
        <SelecaoModulo icon="mdi-warehouse" text="Gestão Estoque" route="/estoque"/>
    </div>
</template>

<style scoped>
#page{
    overflow: auto;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}
</style>