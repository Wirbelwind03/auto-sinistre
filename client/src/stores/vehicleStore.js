import { defineStore } from "pinia"
import { ref, computed, reactive } from 'vue'
import api from '@/services/api'

export const useVehicleStore = defineStore('vehicle', () => {
    const vehicles = ref([])
    const fuelTypes = ref([])

    async function addVehicle(brandId, model, year, mileage, licensePlate, fuelType, vin) {
    const response = await api.post('/vehicle/add', {
        brandId: brandId,
        model: model,
        year: year,
        mileage: mileage,
        licensePlate: licensePlate,
        fuelType: fuelType,
        vin: vin
    })
    }

    async function getUserVehicles() {
        const response = await api.get('/vehicle/my-vehicles')
        vehicles.value = response.data
    }

    async function getFuelTypes() {
        const response = await api.get('/vehicle/fuel-types')
        fuelTypes.value = response.data
    }

    return { vehicles, fuelTypes, addVehicle, getUserVehicles, getFuelTypes }
})