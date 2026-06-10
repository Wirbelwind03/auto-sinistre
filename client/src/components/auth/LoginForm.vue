<style scoped>
  * { font-family: 'Nunito', sans-serif !important; }
</style>

<template>
    <div>
        <div class="text-h6 font-weight-black mb-1 text-blue-darken-4">Bienvenue</div>
        <div class="text-body-2 text-medium-emphasis mb-6">Connectez-vous à votre espace</div>

        <v-text-field
        v-model="email"
        :error-messages="loginErrorsForm.email"
        label="Adresse e-mail"
        variant="outlined"
        density="comfortable"
        prepend-inner-icon="mdi-email-outline"
        class="mb-3"
        color="blue-darken-2"
        rounded="lg"
        ></v-text-field>

        <v-text-field
        v-model="password"
        :error-messages="loginErrorsForm.password"
        type="password"
        label="Mot de passe"
        variant="outlined"
        density="comfortable"
        prepend-inner-icon="mdi-lock-outline"
        class="mb-1"
        color="blue-darken-2"
        rounded="lg"
        ></v-text-field>

        <v-btn
        :loading="loginLoading"
        color="blue-darken-3"
        variant="flat"
        block
        rounded="lg"
        size="large"
        class="font-weight-bold text-uppercase"
        append-icon="mdi-arrow-right"
        @click="handleLogin"
        >
        Se connecter
        </v-btn>

        <v-divider class="my-4">OU</v-divider>

        <v-btn
        variant="outlined"
        block
        rounded="lg"
        color="blue-darken-2"
        class="font-weight-bold text-uppercase"
        prepend-icon="mdi-account-plus"
        @click="$emit('show-register-form')"
        >
        Créer un compte
        </v-btn>
    </div>
</template>
 
<script setup>
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import { reactive, ref } from 'vue'

defineEmits(['show-register-form'])
 
const router = useRouter()
const auth   = useAuthStore()

// Connexion
const email    = ref('')
const password = ref('')

const loginErrorsForm = reactive({
  email: '',
  password: ''
})

const loginLoading  = ref(false)

function handleLogin() {
  Object.keys(loginErrorsForm).forEach(key => loginErrorsForm[key] = '')

  loginLoading.value = true
  auth.login(email.value, password.value)
    .then(() => {
        // snackbar.show = true
        // snackbar.message = 'Connexion réussie ! Redirection...'
        router.push('/')
    })
    .catch((e) => {
      switch (e.response?.status){
        case 401:
          loginErrorsForm.password = "Mauvais mot de passe"
          break

        case 404:
          loginErrorsForm.email = "Compte non trouvé"
          break

        default:
          break
      }
    })
    .finally(() => { loginLoading.value = false } )
}
</script>