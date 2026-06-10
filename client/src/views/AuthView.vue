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
              <LoginForm
                v-if="!showRegister"
                @show-register-form="showRegister = true"
              />
              <RegisterForm
                v-else
                @show-login-form="showRegister = false"
              />
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>

  <!-- <v-snackbar v-model="snackbar.show" :color="snackbar.color" timeout="3000" location="top">
    <v-icon class="mr-2">mdi-check-circle</v-icon>
    {{ snackbar.message }}
    <template v-slot:actions>
      <v-btn variant="text" @click="snackbar.show = false">OK</v-btn>
    </template>
  </v-snackbar> -->
</template>
 
<script setup>
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import { reactive, ref } from 'vue'
import { lettersOnly, validateEmail } from '@/utils/validation'

import LoginForm    from '@/components/auth/LoginForm.vue'
import RegisterForm from '@/components/auth/RegisterForm.vue'
 
const router = useRouter()
const auth   = useAuthStore()

const showRegister = ref(false)

const snackbar = reactive({
  show: false,
  message: '',
  color: 'success'
})
</script>