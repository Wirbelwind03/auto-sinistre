import { defineStore } from "pinia"
import { ref, computed, reactive } from 'vue'
import api from '@/services/api'

export const useAuthStore = defineStore('auth', () => {
    const token = ref(localStorage.getItem('token') || null)
    const userEmail  = ref(localStorage.getItem('email') || null)
    const role  = ref(localStorage.getItem('role') || null)

    const user = reactive(JSON.parse(localStorage.getItem('user')) || {
        role: null,
        email: null,
        firstName: null,
        name: null
    })

    
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

        user.email = null
        user.firstName = null
        user.name = null
        user.role = null

        localStorage.removeItem('token')
        localStorage.removeItem('user')
    }

    function getAuthResponse(authResponse){
        const { token: tok, ...userData } = authResponse

        token.value = tok
        Object.assign(user, userData)

        localStorage.setItem('token', authResponse.token)
        localStorage.setItem('user', JSON.stringify(user))
    }


    return { token, user, isAuthenticated, login, register, logout }
})