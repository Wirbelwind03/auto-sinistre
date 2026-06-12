import { defineStore } from "pinia"
import { ref, computed, reactive } from 'vue'
import api from '@/services/api'

export const useSinistreStore = defineStore('sinistre', () => {
    const types = ref([])
    const damagedPieces = ref([])

    async function getTypes() {
        const response = await api.get('/sinistre/types')
        types.value = response.data
    }

    async function getDamagedPieces() {
        const response = await api.get('/sinistre/damaged-pieces')
        damagedPieces.value = response.data
    }

    return { types, damagedPieces, getTypes, getDamagedPieces }
})