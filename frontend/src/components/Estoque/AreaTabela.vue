<script setup lang="ts">
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
                    <tr>
                        <td>
                            Nome
                        </td>
                        <td>
                            Fornecedor
                        </td>
                        <td>
                            Categoria
                        </td>
                        <td>
                            Valor
                        </td>
                        <td>
                            Quantidade
                        </td>
                        <td>
                            Localização
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

#tabela{
    max-height: 28em;
}
</style>