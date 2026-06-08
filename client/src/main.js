import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { createVuetify } from 'vuetify'
import 'vuetify/styles'
import '@mdi/font/css/materialdesignicons.css'
import App from './App.vue'
import router from './router'

const vuetify = createVuetify({
  theme: { defaultTheme: 'dark' }
})
const pinia = createPinia()

createApp(App)
    .use(pinia)
    .use(router)
    .use(vuetify)
    .mount('#app')
