import { defineStore } from "pinia"
import { ref, computed } from 'vue'
import api from '@/services/api'

export const useAuthStore = defineStore('auth', () => {
    const token = ref(localStorage.getItem('token') || null)
    const userEmail  = ref(localStorage.getItem('email') || null)
    const role  = ref(localStorage.getItem('role') || null)
    
    const isAuthenticated = computed(() => !!token.value)

    async function login(email, password) {
        const response = await api.post('/auth/login', { email, password })
        getAuthResponse(response.data)
    }

    async function register(email, password, name, firstName) {
        const response = await api.post('/auth/register', { email, password, name, firstName })
        getAuthResponse(response.data)
    }

    function logout(){
        token.value = null
        userEmail.value  = null
        // role.value  = null

        localStorage.removeItem('token')
        localStorage.removeItem('email')
        // localStorage.removeItem('role')
    }

    function getAuthResponse(authResponse){
        token.value = authResponse.token
        userEmail.value = authResponse.email
        // role.value = authResponse.role
        
        localStorage.setItem('token', authResponse.token)
        localStorage.setItem('email', authResponse.email)
        // localStorage.setItem('role', authResponse.role)
    }


    return { token, userEmail, isAuthenticated, login, register, logout }
})