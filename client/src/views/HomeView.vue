<template>
  <div class="container">
    <p>Connecté en tant que <strong>{{ auth.userEmail }}</strong></p>
    <button @click="handleLogout">Se déconnecter</button>
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