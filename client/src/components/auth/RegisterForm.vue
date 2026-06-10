<style scoped>
  * { font-family: 'Nunito', sans-serif !important; }
</style>

<template>
    <div>
        <div class="d-flex align-center ga-2 mb-5">
            <v-btn icon="mdi-arrow-left" variant="text" size="small" @click="$emit('show-login-form')"></v-btn>
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
</template>
 
<script setup>
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import { reactive, ref } from 'vue'
import { lettersOnly, validateEmail } from '@/utils/validation'

defineEmits(['show-login-form'])
 
const router = useRouter()
const auth   = useAuthStore()

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
        // snackbar.show = true
        // snackbar.message = 'Inscription réussi ! Redirection...'
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