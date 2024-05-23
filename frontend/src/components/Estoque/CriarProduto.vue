<script setup lang="ts">
import { Ref, ref } from 'vue';
import { criaCategoria, criaFornecedor, criaProduto, mostrarNovoProduto, recuperaTodosProdutos, categorias, fornecedores } from '../../services/estoqueService';
import { Produto } from '../../models/Produto';
import { Categoria } from '../../models/Categoria';
import { Fornecedor } from '../../models/Fornecedor';

const formValid: Ref<boolean> = ref()
const categoriaAutocomplete: Ref<any> = ref({ filteredItems: [] })
const fornecedorAutocomplete: Ref<any> = ref({ filteredItems: [] })
const loadingCategoria: Ref<boolean> = ref(false)
const loadingFornecedor: Ref<boolean> = ref(false)

const rules = {
    required: (valor: string) => !!valor || 'Campo Obrigatório'
}

const novoProduto: Ref<Produto> = ref(new Produto())
const novaCategoria: Ref<Categoria> = ref(new Categoria())
const novoFornecedor: Ref<Fornecedor> = ref(new Fornecedor())

    const salvarProduto = async () => {
    if (formValid.value) {
        mostrarNovoProduto.value = false
        await criaProduto(novoProduto.value)
        await recuperaTodosProdutos()
    }
}

const salvarCategoria = async (nomeCategoria: string) => {
    loadingCategoria.value = true
    novaCategoria.value.nome = nomeCategoria
    await criaCategoria(novaCategoria.value)
    await recuperaTodosProdutos()
    loadingCategoria.value = false
}

const salvarFornecedor = async (nomeFornecedor: string) => {
    loadingFornecedor.value = true
    novoFornecedor.value.nome = nomeFornecedor
    await criaFornecedor(novoFornecedor.value)
    await recuperaTodosProdutos()
    loadingFornecedor.value = false
}

</script>
<template>
    <VCard>
        <VForm v-model="formValid" ref="form" @submit.prevent="salvarProduto">
            <VToolbar class="toolbar text-white mb-5">
                <VBtn icon="mdi-close" @click="mostrarNovoProduto = false"></VBtn>

                <VToolbarTitle>Cadastrar Novo Produto</VToolbarTitle>

                <VSpacer></VSpacer>

                <VToolbarItems>
                    <VBtn type="submit"> Salvar Produto</VBtn>
                </VToolbarItems>
            </VToolbar>

            <VRow>
                <VCol>
                    <VTextField :rules="[rules.required]" label="Nome" placeholder="Nome do Produto" hide-details="auto"
                        v-model="novoProduto.name" />
                </VCol>
            </VRow>
            <VRow>
                <VCol md="6">

                    <VAutocomplete no-data-text="Nenhum Resultado" :rules="[rules.required]" ref="categoriaAutocomplete" label="Categoria"
                        placeholder="Categoria do Produto" return-object :items="categorias" item-title="nome"
                        v-model="novoProduto.categoria" hide-details="auto">

                        <template v-if="mostrarNovoProduto" #prepend-inner>
                            <VBtn @click="salvarCategoria(categoriaAutocomplete?.modelValue)" color="success"
                                v-if="categoriaAutocomplete?.filteredItems.length == 0 && categoriaAutocomplete.modelValue != ''">
                                Criar Categoria {{ categoriaAutocomplete?.modelValue }}
                            </VBtn>
                        </template>

                        <template v-if="loadingCategoria" #prepend-inner>
                            <div class="text-center">
                                <VProgressCircular color="#5C4BAA" indeterminate/>
                            </div>
                        </template>
                    </VAutocomplete>
                </VCol>

                <VCol md="6">
                    <VAutocomplete no-data-text="Nenhum Resultado" :rules="[rules.required]" ref="fornecedorAutocomplete" label="Fornecedor"
                        placeholder="Fornecedor do Produto" return-object :items="fornecedores" item-title="nome"
                        v-model="novoProduto.fornecedor" hide-details="auto">
                        <template v-if="mostrarNovoProduto" #prepend-item>
                            <VBtn @click="salvarFornecedor(fornecedorAutocomplete?.modelValue)" color="success"
                                v-if="fornecedorAutocomplete?.filteredItems.length == 0 && fornecedorAutocomplete.modelValue != ''">
                                Criar Fornecedor {{ fornecedorAutocomplete?.modelValue }}
                            </VBtn>
                        </template>

                        <template v-if="loadingFornecedor" #prepend-item>
                            <div class="text-center">
                                <VProgressCircular color="#5C4BAA" indeterminate/>
                            </div>
                        </template>
                    </VAutocomplete>
                </VCol>
            </VRow>
            <VRow>
                <VCol md="6">
                    <VTextField :rules="[rules.required]" label="Valor (R$)" placeholder="Valor do Produto"
                        hide-details="auto" v-model="novoProduto.valor" />
                </VCol>
                <VCol md="6">
                    <VTextField :rules="[rules.required]" label="Quantidade" placeholder="Quantidade do Produto"
                        hide-details="auto" v-model="novoProduto.quantidade" />
                </VCol>
            </VRow>
            <VRow>
                <VTextField label="Localização" placeholder="Localização do Produto" hide-details="auto"
                    v-model="novoProduto.localizacao" />
            </VRow>
        </VForm>
    </VCard>
</template>

<style scoped>
.toolbar {
    background-color: #5C4BAA;
}
</style>