<style scoped>
</style>

<template>
  <div class="d-flex flex-row justify-center align-center">
    <v-card width="400" class="pa-8 rounded-lg">

      <div class="text-h6 mb-1 font-weight-black text-uppercase">Auto Assureur</div>

      <!-- Connexion -->
      <div>
          <v-text-field
            label="Adresse e-mail"
            type="email"
          ></v-text-field>

          <v-text-field
            label="Mot de passe"
            type="password"
          ></v-text-field>

          <v-btn
            class="text-uppercase font-weight-bold"
            color="blue-darken-3"
            size="large"
            width="100%"
            append-icon="mdi-arrow-right"
          >
            Se connecter
          </v-btn>

          <v-divider class="my-4">ou</v-divider>

          <v-btn
            class="text-uppercase font-weight-bold"
            variant="outlined"
            color="blue-darken-2"
            size="large"
            flat
            width="100%"
            prepend-icon="mdi-account-plus"
          >
            Créer un compte
          </v-btn>
      </div>
    </v-card>

  </div>
</template>
 
<script setup>
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
 
const router = useRouter()
const auth   = useAuthStore()
 
function handleLogout() {
  auth.logout()
  router.push('/login')
}
</script>
 
<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  gap: 16px;
}
button {
  padding: 8px 16px;
  cursor: pointer;
}
</style>