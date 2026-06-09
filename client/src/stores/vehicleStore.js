import { defineStore } from "pinia"
import { ref, computed, reactive } from 'vue'
import api from '@/services/api'

export const useVehicleStore = defineStore('vehicle', () => {
    const vehicles = ref([])

    async function addVehicle(brandId, model, year, mileage, licensePlate, vin) {
    const response = await api.post('/vehicle/add', {
        brandId: brandId,
        model: model,
        year: year,
        mileage: mileage,
        licensePlate: licensePlate,
        vin: vin
    })
    }

    async function getUserVehicles() {
        const response = await api.get('/vehicle/my-vehicles')
        vehicles.value = response.data
    }

    return { vehicles, addVehicle, getUserVehicles }
})