import { defineStore } from "pinia"
import { ref, computed, reactive } from 'vue'
import api from '@/services/api'

export const useSinistreFormStore = defineStore('sinistreForm', () => {
    const licensePlate = ref('')
    const vehicle = ref(null)

    const type = ref('')
    const severity = ref('')
    const severityLabel = computed(() => {
        const map = {
            FAIBLE: { label: 'Faible', color: 'green', valClass: 'text-green' },
            MOYENNE: { label: 'Moyenne', color: 'orange', valClass: 'text-orange' },
            ELEVEE: { label: 'Élevée', color: 'red', valClass: 'text-red' }
        }
        return map[severity.value] || '—'
    })

    const location = ref(null)
    const date = ref('')
    const hour = ref('')

    const damagedPieces = ref([])

    return { licensePlate, vehicle, type, severity, severityLabel, location, date, hour, damagedPieces }
})