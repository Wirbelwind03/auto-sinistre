<template>
    <v-card id="section-1" rounded="xl" class="mb-5" elevation="1">
        <v-card-title class="d-flex align-center ga-3 pa-5 pb-3">
        <v-avatar color="blue-darken-1" rounded="lg" size="36">
            <v-icon size="20">mdi-car</v-icon>
        </v-avatar>
        <div>
            <div class="text-subtitle-1 font-weight-bold">Véhicule concerné</div>
            <div class="text-caption text-medium-emphasis">Plaque ou sélection dans vos contrats</div>
        </div>
        </v-card-title>
        <v-divider></v-divider>
        <v-card-text class="pa-5">
        <v-row>
            <v-col cols="6">
            <v-autocomplete
                v-model="selectedLicensePlate"
                :items="vehicleStore.vehicles"
                label="Plaque d'immatriculation *"
                item-title="licensePlate"
                item-value="licensePlate"
                variant="outlined"
                density="comfortable"
                prepend-inner-icon="mdi-card-account-details"
                placeholder="XX-000-XX"
                :style="{fontFamily: 'Nunito', fontWeight: 700, letterSpacing: '0.1em', textTransform: 'uppercase'}"
                persistent-hint
                color="blue-darken-2"
                clearable
            ></v-autocomplete>
            </v-col>
        </v-row>

        <!-- Informations du véhicule -->
        <v-card v-if="selectedVehicle" rounded="lg" color="green-lighten-5" class="vehicle-found-card mt-1" elevation="0">
            <v-card-text class="d-flex align-center ga-3 pa-4">
            <v-avatar color="green-lighten-3" rounded="lg" size="44">
                <v-icon color="green-darken-2" size="24">mdi-car-hatchback</v-icon>
            </v-avatar>
            <div class="flex-grow-1">
                <div class="font-weight-bold text-body-2">{{selectedVehicle.brand}} {{selectedVehicle.model}} · {{selectedVehicle.color}}</div>
                <div class="text-caption text-medium-emphasis">{{selectedVehicle.year}} · {{selectedVehicle.fuelType}}</div>
            </div>
            <v-chip color="success" size="small" prepend-icon="mdi-check-circle" variant="flat">Véhicule trouvé</v-chip>
            </v-card-text>
        </v-card>
        </v-card-text>
    </v-card>
</template>

<script setup>
import { onMounted, ref, computed, watch } from 'vue'

import { useVehicleStore } from '@/stores/vehicleStore.js'
import { useStepperBarStore } from '@/stores/stepperBarStore'

const vehicleStore = useVehicleStore()
const stepperBarStore = useStepperBarStore()

onMounted(async() => {
    await vehicleStore.getUserVehicles()
    .then((response) => {
      console.log('Véhicules :', vehicleStore.vehicles)
    })
    .catch((e) => {
      console.error('Erreur lors du chargement des voitures :', e)
    })
})

const selectedLicensePlate = ref('')
const selectedVehicle = computed(() =>
  vehicleStore.vehicles.find(vehicle => vehicle.licensePlate === selectedLicensePlate.value)
)
watch(selectedVehicle, (val) => {
  if (val != null && stepperBarStore.currentStep < 2){
    stepperBarStore.currentStep = 2
  }
  else {
    stepperBarStore.currentStep = 1
  }
})
</script>