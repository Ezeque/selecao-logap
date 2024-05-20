/**
 * main.ts
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Plugins
import { registerPlugins } from '@/plugins'

// Components
import App from './App.vue'

// Composables
import { createApp } from 'vue'
import indexPage from './components/Pages/indexPage.vue'
import EstoquePage from './components/Pages/EstoquePage.vue'
import VogalPage from './components/Pages/VogalPage.vue'

const routes = [
    { path: '/', component: indexPage },
    { path: '/estoque', component: EstoquePage},
    { path: '/vogal', component: VogalPage}
]

const router = createRouter({
    history: createMemoryHistory(),
    routes,
})
//router 
import { createMemoryHistory, createRouter } from 'vue-router'
const app = createApp(App)

registerPlugins(app)

app.use(router).mount('#app')
