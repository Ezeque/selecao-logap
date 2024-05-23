<script lang="ts" setup>
import { VCol, VProgressCircular, VTextField } from 'vuetify/components';
import { recuperaVogal, arrayChars, frase, alvo, mostrarResultado, loading } from '../../services/vogalServices';


</script>

<template>
    <VRow id="layout-vogal">
        <VCol cols="12" md="4" class="text-center">
            <VIcon size="80" icon="mdi-text-box-search-outline" />
            <h2 class="my-5">Encontrar Vogal</h2>
            <VForm @submit.prevent="recuperaVogal(frase)" class="d-flex justify-end align-center mb-5">
                <VTextField class="mr-5 border rounded" 
                label="Frase" 
                placeholder="Digite a Sua Frase" 
                id="input-vogal" 
                v-model="frase" hide-details/>
                <VBtn type="submit" id="botao-encontrar" color="#5C4BAA" >
                    <VIcon size="30" icon="mdi-magnify"/>
                </VBtn>
            </VForm>

            <VProgressCircular v-if="loading" indeterminate />

            <div v-if="mostrarResultado && !loading" id="resultado">
                <span class="">
                    Resultado:
                </span>
                <span v-for="(char, index) in arrayChars" :key="'char - ' + index" >
                    <span style="font-size:1.7em" v-if="char == alvo" class="text-error">
                        {{ char }}
                    </span>
                    <span v-else>
                        {{ char }}
                    </span>
                </span>
            </div>
        </VCol>
    </VRow>
    <VRow>
        
    </VRow>
</template>

<style scoped>
#layout-vogal {
    color: #5C4BAA;
    overflow: auto;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
}

#input-vogal {
    width: 10%;
    height: 1em;
}

#botao-encontrar{
    height: 4.5em;
    font-size: 0.8em;
}

#resultado{
    color: #5C4BAA;
    font-size: 1.1em;
    text-align: left;
    font-weight: bold;
}
</style>