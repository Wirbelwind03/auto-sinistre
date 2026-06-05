import { defineStore } from "pinia"
import { ref, computed } from 'vue'
import api from '@/services/api'

export const useAuthStore = defineStore('auth', () => {
    const token = ref(localStorage.getItem('token') || null)
    const userEmail  = ref(localStorage.getItem('email') || null)
    
    const isAuthenticated = computed(() => !!token.value)

    async function login(email, password) {
        const { data } = await api.post('/auth/login', { email, password })
        token.value = data.token
        userEmail.value = data.email
        localStorage.setItem('token', data.token)
        localStorage.setItem('email', data.email)
    }

    function logout(){
        token.value = null
        userEmail.value  = null
        localStorage.removeItem('token')
        localStorage.removeItem('email')

    }

    return { token, userEmail, isAuthenticated, login, logout }
})