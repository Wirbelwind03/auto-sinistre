import { defineStore } from "pinia"
import { ref, computed, reactive } from 'vue'
import api from '@/services/api'

export const useBrandStore = defineStore('brand', () => {
    const brands = ref([])

    async function getAllBrands() {
        const response = await api.get('/brand/brands')
        brands.value = response.data
    }

    return { brands, getAllBrands }
})