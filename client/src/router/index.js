import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const routes = [
    {
        path: '/login', 
        name: 'Login',
        component: () => import('@/views/LoginView.vue'),
        meta: { requiresGuest: true }
    },
    {
        path: '/',
        name: 'Home',
        component: () => import('@/views/HomeView.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/vehicle',
        name: 'Vehicle',
        component: () => import('@/views/VehicleView.vue'),
        meta: { requiresAuth: true }
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

router.beforeEach((to) => {
    const auth = useAuthStore()

    if (to.meta.requiresAuth && !auth.isAuthenticated) {
        return { name: 'Login' }
    }

    if (to.meta.requiresGuest && auth.isAuthenticated) {
        return { name: 'Home' }
    }
})

export default router;