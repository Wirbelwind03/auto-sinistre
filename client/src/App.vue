<template>
  <v-app>
    <SideBar v-if="auth.isAuthenticated"/>
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>
 
<script setup>
  import SideBar from './components/SideBar.vue'
  import { useAuthStore } from '@/stores/auth'

  const auth = useAuthStore()
</script>
