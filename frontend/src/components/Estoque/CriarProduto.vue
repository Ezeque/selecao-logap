<script setup lang="ts">
import { useDisplay } from 'vuetify'
import { Ref, onMounted, ref } from 'vue';
import { criaCategoria, criaFornecedor, criaProduto, mostrarCriacao, recuperaTodosProdutos, categorias, fornecedores } from '../../services/estoqueService';
import { Produto } from '../../models/Produto';
import { Categoria } from '../../models/Categoria';
import { Fornecedor } from '../../models/Fornecedor';
import { VExpansionPanel, VExpansionPanelText, VExpansionPanelTitle, VForm, VProgressCircular } from 'vuetify/components';

const formValid: Ref<boolean> = ref()
const formValidCategoria: Ref<boolean> = ref()
const formValidFornecedor: Ref<boolean> = ref()
const categoriaAutocomplete: Ref<any> = ref({ filteredItems: [] })
const fornecedorAutocomplete: Ref<any> = ref({ filteredItems: [] })
const loadingCategoria: Ref<boolean> = ref(false)
const loadingFornecedor: Ref<boolean> = ref(false)
const loadingProduto: Ref<boolean> = ref(false)
const isMobile = useDisplay().smAndDown


const rules = {
    required: (valor: string) => !!valor || 'Campo Obrigatório'
}

const novoProduto: Ref<Produto> = ref(new Produto())
const novaCategoria: Ref<Categoria> = ref(new Categoria())
const novoFornecedor: Ref<Fornecedor> = ref(new Fornecedor())

const salvarProduto = async () => {
    if (formValid.value) {
        loadingProduto.value = true
        mostrarCriacao.value = false
        await criaProduto(novoProduto.value)
        loadingProduto.value = false
        await recuperaTodosProdutos()
    }
}

const salvarCategoria = async (nomeCategoria: string) => {
    if (formValidCategoria.value) {
        loadingCategoria.value = true
        novaCategoria.value.nome = nomeCategoria
        await criaCategoria(novaCategoria.value)
        mostrarCriacao.value = false
        loadingCategoria.value = false
        await recuperaTodosProdutos()
    }
}

const salvarFornecedor = async (nomeFornecedor: string) => {
    if (formValidFornecedor.value) {
        loadingFornecedor.value = true
        novoFornecedor.value.nome = nomeFornecedor
        await criaFornecedor(novoFornecedor.value)
        mostrarCriacao.value = false
        loadingFornecedor.value = false
        await recuperaTodosProdutos()
    }
}
</script>
<template>
    <VCard>
        <VToolbar class="toolbar text-white mb-5">
            <VBtn icon="mdi-close" @click="mostrarCriacao = false"></VBtn>

            <VToolbarTitle>Cadastrar</VToolbarTitle>
        </VToolbar>
        <VExpansionPanels>
            <VExpansionPanel>
                <VExpansionPanelTitle>
                    <h3>Cadastrar Produtos</h3>
                </VExpansionPanelTitle>
                <VExpansionPanelText>

                    <VForm v-model="formValid" ref="form" @submit.prevent="salvarProduto">
                        <VRow>
                            <VCol>
                                <VTextField :rules="[rules.required]" label="Nome" placeholder="Nome do Produto"
                                    hide-details="auto" v-model="novoProduto.name" />
                            </VCol>
                        </VRow>
                        <VRow>
                            <VCol md="6">
                                <VAutocomplete no-data-text="Nenhum Resultado" :rules="[rules.required]"
                                    ref="categoriaAutocomplete" label="Categoria" placeholder="Categoria do Produto"
                                    return-object :items="categorias" item-title="nome" v-model="novoProduto.categoria"
                                    hide-details="auto">
                                </VAutocomplete>
                            </VCol>
                            <VCol md="6">
                                <VAutocomplete no-data-text="Nenhum Resultado" :rules="[rules.required]"
                                    ref="fornecedorAutocomplete" label="Fornecedor" placeholder="Fornecedor do Produto"
                                    return-object :items="fornecedores" item-title="nome"
                                    v-model="novoProduto.fornecedor" hide-details="auto">
                                </VAutocomplete>
                            </VCol>
                        </VRow>
                        <VRow>
                            <VCol md="6">
                                <VTextField :rules="[rules.required]" label="Valor (R$)" placeholder="Valor do Produto"
                                    hide-details="auto" v-model="novoProduto.valor" />
                            </VCol>
                            <VCol md="6">
                                <VTextField :rules="[rules.required]" label="Quantidade"
                                    placeholder="Quantidade do Produto" hide-details="auto"
                                    v-model="novoProduto.quantidade" />
                            </VCol>
                        </VRow>
                        <VRow>
                            <VTextField label="Localização" placeholder="Localização do Produto" hide-details="auto"
                                v-model="novoProduto.localizacao" />
                        </VRow>
                        <VRow>
                            <VCol>
                                <VBtn type="submit" color="success" :width="isMobile ? '100%' : 'auto'">
                                    <span v-if="!loadingProduto">Salvar</span>
                                    <VProgressCircular v-else indeterminate />
                                </VBtn>
                            </VCol>
                        </VRow>

                    </VForm>
                </VExpansionPanelText>
            </VExpansionPanel>

            <VExpansionPanel>
                <VExpansionPanelTitle>
                    <h3>Cadastrar Categoria</h3>
                </VExpansionPanelTitle>
                <VExpansionPanelText>
                    <VForm v-model="formValidCategoria" @submit.prevent="salvarCategoria(novaCategoria.nome)">
                        <VTextField label="Nome" placeholder="Nome da Categoria" :rules="[rules.required]"
                            v-model="novaCategoria.nome" />

                        <VBtn type="submit" color="success" :width="isMobile ? '100%' : 'auto'">
                            <span v-if="!loadingCategoria">Salvar</span>
                            <VProgressCircular v-else indeterminate />
                        </VBtn>

                    </VForm>
                </VExpansionPanelText>
            </VExpansionPanel>

            <VExpansionPanel>
                <VExpansionPanelTitle>
                    <h3>Cadastrar Fornecedor</h3>
                </VExpansionPanelTitle>
                <VExpansionPanelText>
                    <VForm v-model="formValidFornecedor" @submit.prevent="salvarFornecedor(novoFornecedor.nome)">
                        <VTextField label="Nome" placeholder="Nome do Fornecedor" :rules="[rules.required]"
                            v-model="novoFornecedor.nome" />

                        <VBtn type="submit" color="success" :width="isMobile ? '100%' : 'auto'">
                            <span v-if="!loadingFornecedor">Salvar</span>
                            <VProgressCircular v-else indeterminate />
                        </VBtn>

                    </VForm>
                </VExpansionPanelText>
            </VExpansionPanel>
        </VExpansionPanels>


    </VCard>
</template>

<style scoped>
.toolbar {
    background-color: #5C4BAA;
}
</style>