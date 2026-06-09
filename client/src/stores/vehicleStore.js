import { defineStore } from "pinia"
import { ref, computed, reactive } from 'vue'
import api from '@/services/api'

export const useVehicleStore = defineStore('vehicle', () => {
    async function addVehicle(brandId, model, year, mileage, licensePlate) {
        console.log('brandId reçu :', brandId)
    const response = await api.post('/vehicle/add', {
        brandId: brandId,
        model: model,
        year: year,
        mileage: mileage,
        licensePlate: licensePlate
    })
    }

    return { addVehicle }
})