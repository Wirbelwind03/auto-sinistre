<style scoped>
  * { font-family: 'Nunito', sans-serif !important; }

  .auth-bg {
    min-height: 100vh;
    background: linear-gradient(135deg, #0d1b4b 0%, #1a237e 40%, #1565c0 100%);
    position: relative;
    overflow: hidden;
  }

  /* Decorative circles */
  .bg-circle {
    position: absolute;
    border-radius: 50%;
    opacity: 0.07;
    background: white;
  }
  .bg-circle-1 { width: 500px; height: 500px; top: -150px; left: -100px; }
  .bg-circle-2 { width: 350px; height: 350px; bottom: -80px; right: 100px; }
  .bg-circle-3 { width: 180px; height: 180px; top: 200px; right: 50px; opacity: 0.04; }

  .login-card {
    backdrop-filter: blur(12px);
    background: rgb(var(--v-theme-surface)) !important;
    border: 1px solid rgba(var(--v-theme-on-surface), 0.12) !important;
  }

  .role-chip {
    cursor: pointer;
    transition: all .2s !important;
    border: 2px solid transparent !important;
    font-weight: 700 !important;
  }

  .role-chip.selected-admin {
    border-color: #1565c0 !important;
    background: #e3f2fd !important;
    color: #1565c0 !important;
  }
  .role-chip.selected-gestionnaire {
    border-color: #6a1b9a !important;
    background: #f3e5f5 !important;
    color: #6a1b9a !important;
  }
  .role-chip.selected-client {
    border-color: #2e7d32 !important;
    background: #e8f5e9 !important;
    color: #2e7d32 !important;
  }

  .feature-item {
    display: flex;
    align-items: flex-start;
    gap: 12px;
    margin-bottom: 20px;
  }

  @keyframes fadeUp {
    from { opacity: 0; transform: translateY(20px); }
    to { opacity: 1; transform: translateY(0); }
  }

  .login-card { animation: fadeUp .5s ease both; }
  .left-panel > * { animation: fadeUp .5s ease both; }
  .left-panel > *:nth-child(1) { animation-delay: .1s; }
  .left-panel > *:nth-child(2) { animation-delay: .2s; }
  .left-panel > *:nth-child(3) { animation-delay: .3s; }
  .left-panel > *:nth-child(4) { animation-delay: .4s; }
</style>

<template>
  <div class="auth-bg d-flex align-center justify-center">
    <v-container>
      <v-row align="center" justify="center" style="min-height:100vh">
        <v-col cols="12" md="5" lg="4">
          <v-card class="login-card pa-2" rounded="xl" elevation="24">
            <v-card-text class="pa-8">
              <!-- Connexion -->
              <div v-if="!showRegister">
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
                  @click="showRegister = true"
                >
                  Créer un compte
                </v-btn>
              </div>

              <!-- Inscription -->
              <div v-else>
                <div class="d-flex align-center ga-2 mb-5">
                  <v-btn icon="mdi-arrow-left" variant="text" size="small" @click="showRegister = false"></v-btn>
                  <div>
                    <div class="text-h6 font-weight-black text-blue-darken-4">Créer un compte</div>  
                  </div>
                </div>

                <v-row>
                  <v-col cols="6" class="pb-0">
                    <v-text-field 
                      v-model="registerForm.firstName" 
                      :error-messages="registerFormErrors.firstName"
                      @keypress="lettersOnly"
                      label="Prénom *" variant="outlined" 
                      density="comfortable" rounded="lg" 
                      color="blue-darken-2"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="6" class="pb-0">
                    <v-text-field 
                      v-model="registerForm.name" 
                      :error-messages="registerFormErrors.name"
                      @keypress="lettersOnly"
                      label="Nom *" 
                      variant="outlined" 
                      density="comfortable" 
                      rounded="lg" 
                      color="blue-darken-2"
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-text-field 
                  v-model="registerForm.email" 
                  :error-messages="registerFormErrors.email"
                  label="E-mail *" 
                  variant="outlined" 
                  density="comfortable" 
                  prepend-inner-icon="mdi-email-outline" 
                  class="mb-3" 
                  rounded="lg" 
                  color="blue-darken-2"
                ></v-text-field>
                <!-- <v-text-field label="Téléphone" variant="outlined" density="comfortable" prepend-inner-icon="mdi-phone" class="mb-3" rounded="lg" color="blue-darken-2"></v-text-field> -->

                <v-text-field 
                  v-model="registerForm.password"
                  :error-messages="registerFormErrors.password"
                  label="Mot de passe *" 
                  variant="outlined" density="comfortable" 
                  prepend-inner-icon="mdi-lock-outline" 
                  type="password" 
                  class="mb-3" 
                  rounded="lg" 
                  color="blue-darken-2"
                ></v-text-field>
                <v-text-field 
                  v-model="registerForm.confirmPassword"
                  :error-messages="registerFormErrors.confirmPassword"
                  label="Confirmer le mot de passe *" 
                  variant="outlined" density="comfortable" 
                  prepend-inner-icon="mdi-lock-check" 
                  type="password" class="mb-4" 
                  rounded="lg" 
                  color="blue-darken-2"
                ></v-text-field>
              
                <v-btn 
                  :loading="registerLoading"
                  color="blue-darken-3" 
                  variant="flat" block 
                  rounded="lg" size="large" 
                  class="font-weight-bold text-uppercase" 
                  append-icon="mdi-account-check"
                  @click="handleRegister"
                >
                  Créer mon compte
                </v-btn>
              </div>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>

  <v-snackbar v-model="snackbar.show" :color="snackbar.color" timeout="3000" location="top">
    <v-icon class="mr-2">mdi-check-circle</v-icon>
    {{ snackbar.message }}
    <template v-slot:actions>
      <v-btn variant="text" @click="snackbar.show = false">OK</v-btn>
    </template>
  </v-snackbar>
</template>
 
<script setup>
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import { reactive, ref } from 'vue'
import { lettersOnly, validateEmail } from '@/utils/validation'
 
const router = useRouter()
const auth   = useAuthStore()

const showRegister = ref(false)

const snackbar = reactive({
  show: false,
  message: '',
  color: 'success'
})

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
        snackbar.show = true
        snackbar.message = 'Connexion réussie ! Redirection...'
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

// Incrisption
const registerForm = reactive({
  email: '',
  password: '',
  confirmPassword: '',
  name: '',
  firstName: '',
})

const registerFormErrors = reactive({
  email: '',
  password: '',
  confirmPassword: '',
  name: '',
  firstName: '',
})

const registerLoading = ref(false)

function handleRegister(){
  if (validateRegistration()) { return } 

  registerLoading.value = true; 
  auth.register(registerForm.email, registerForm.password, registerForm.name, registerForm.firstName)
    .then(() => {
        snackbar.show = true
        snackbar.message = 'Inscription réussi ! Redirection...'
        router.push('/')
    })
    .catch((e) => {
      if (e.response?.status === 409){
        registerFormErrors.email = "Email déjà utilisé"
      }
    })
    .finally(() => { registerLoading.value = false})
}

function validateRegistration(){
  let hasErrors = false

  Object.keys(registerFormErrors).forEach(key => registerFormErrors[key] = '')

  Object.keys(registerFormErrors).forEach(key => {
    if (!registerForm[key]) {
      registerFormErrors[key] = 'Requis'
      hasErrors = true
    }
  })

  // Vérifier si la confirmation de mot de passe est bon
  if (registerForm.password !== registerForm.confirmPassword) {
    registerFormErrors.confirmPassword = 'Les mots de passe ne correspondent pas'
    hasErrors = true
  }

  // Si un email à été rentrée, véfifier s'il est valide
  if (!registerFormErrors.email) {
      if (!validateEmail(registerForm.email)){
        registerFormErrors.email = 'Format d\'email invalide'
        hasErrors = true
      }
  }

  return hasErrors
}

</script>