<script setup>
import { defineProps } from 'vue';
import { useRouter } from 'vue-router';
import { VIcon } from 'vuetify/components';
const props = defineProps({
    icon: String,
    text: String,
    route: {type: String, required: true}
})

const router = useRouter()

const gotToModule = () => {
    router.push(props.route)
}


</script>

<template>
    <div id="botao" @click="gotToModule">
        <VIcon size="50" :icon="icon" />
        <span>{{text}}</span>
    </div>
</template>

<style scoped>
#botao {
    height: 8em;
    width: 8em;
    border: 1px solid #5C4BAA;
    color: #5C4BAA;
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    transition: 0.3s all ease;
    font-weight: bold;
    text-align: center;
    padding: 0;
}

#botao:hover {
    background-color: #5C4BAA;
    color: white;
    cursor: pointer;
    scale: 120%;
}
</style>