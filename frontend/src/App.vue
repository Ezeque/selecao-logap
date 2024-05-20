<template>
  <v-app>
    <v-main>
      <VBtn v-if="$route.path != '/'" id="go-back" icon="mdi-arrow-left" color="#5C4BAA" @click="$router.back()">
        
      </VBtn>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script setup lang="ts">
import SelecaoModulo from './components/Utils/SelecaoModulo.vue'
</script>
<style scoped>
#go-back{
 float: left;
 margin: 1em; 
}
</style>
