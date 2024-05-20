<script setup>
</script>

<!-- FORMULÁRIO DE FILTRAGEM DE PRODUTOS -->

<template>
    <VForm>
        <VRow>
            <VCol md="11" cols="12">
                <VRow>
                    <VCol cols="12" class="pb-0 mt-1">
                        <VTextField label="Produto:" placeholder="Nome do Produto"></VTextField>
                    </VCol>
                </VRow>
                <VRow>
                    <VCol cols="6" class="py-0">
                        <VTextField label="Categoria:" placeholder="Nome da Categoria"></VTextField>
                    </VCol>
                    <VCol cols="6" class="py-0">
                        <VTextField label="Fornecedor:" placeholder="Nome do Fornecedor"></VTextField>
                    </VCol>
                </VRow>
                <VRow>
                    <VCol cols="12" class="d-flex justify-end py-0">
                        <VCheckbox label="Mostrar Produtos Esgotados" />
                    </VCol>
                </VRow>
                <VRow>
                    <VCol cols="12" class="d-flex justify-center py-0">
                        <VBtn color="#5C4BAA" width="100%">Filtrar Resultados</VBtn>
                    </VCol>
                </VRow>
            </VCol>
        </VRow>
    </VForm>
</template>

<style scoped></style>