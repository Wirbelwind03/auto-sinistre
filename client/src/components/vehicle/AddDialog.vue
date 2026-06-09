<style scoped>
</style>

<template>
  <v-dialog v-model="addDialog" max-width="560">
    <v-card rounded="xl">
      <v-card-title class="d-flex align-center ga-3 pa-6 pb-4">
        <v-avatar color="blue-darken-2" rounded="lg" size="38">
          <v-icon color="white">mdi-car-plus</v-icon>
        </v-avatar>
        <div>
          <div class="text-h6 font-weight-black" style="color:#0d1b4b">Nouveau véhicule</div>
          <div class="text-caption text-medium-emphasis">Enregistrer un véhicule assuré</div>
        </div>
        <v-spacer></v-spacer>
        <v-btn icon="mdi-close" variant="text" size="small" @click="addDialog = false"></v-btn>
      </v-card-title>
      <v-divider></v-divider>
      <v-card-text class="pa-6">
        <v-text-field
          v-model="addVehicleForm.licensePlate" 
          label="Plaque d'immatriculation *"
          variant="outlined"
          density="comfortable"
          rounded="lg"
          prepend-inner-icon="mdi-card-account-details"
          class="mb-3"
          color="blue-darken-2"
          hint="Format français XX-000-XX"
          maxlength="9"
          persistent-hint
          style="font-weight:800;letter-spacing:.1em;text-transform:uppercase"
        ></v-text-field>

        <v-row>
          <v-col cols="6">
            <v-autocomplete
              v-model="addVehicleForm.brand"
              :items="brandStore.brands"
              item-title="name"
              item-value="id"
              :loading="loadingBrands"
              label="Marque *" 
              variant="outlined" 
              density="comfortable" 
              rounded="lg" 
              color="blue-darken-2"  
              clearable
              no-data-text="Aucune marque trouvée"
            ></v-autocomplete>
          </v-col>
          <v-col cols="6">
            <v-text-field 
              v-model="addVehicleForm.model" 
              label="Modèle *" 
              variant="outlined" 
              density="comfortable" 
              rounded="lg" 
              color="blue-darken-2"
            ></v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="4">
            <v-select
              v-model="addVehicleForm.year" 
              label="Année *"
              :items="years"
              variant="outlined"
              density="comfortable"
              rounded="lg"
              color="blue-darken-2"
            ></v-select>
          </v-col>
          <v-col cols="4">
            <v-select 
              v-model="addVehicleForm.fuel" 
              label="Carburant *" 
              :items="['Essence', 'Diesel', 'Électrique', 'Hybride', 'GPL']" 
              variant="outlined" 
              density="comfortable" 
              rounded="lg" 
              color="blue-darken-2"
            ></v-select>
          </v-col>
          <v-col cols="4">
            <v-text-field 
              v-model="addVehicleForm.color" 
              label="Couleur" 
              variant="outlined" 
              density="comfortable" 
              rounded="lg" 
              color="blue-darken-2"
            ></v-text-field>
          </v-col>
        </v-row>

        <v-text-field 
          v-model="addVehicleForm.vin" 
          label="N° VIN (châssis)" 
          variant="outlined" 
          density="comfortable" 
          rounded="lg" 
          prepend-inner-icon="mdi-identifier" 
          class="mb-3" 
          color="blue-darken-2" 
          hint="17 caractères" 
          maxlength="17"
          persistent-hint
        ></v-text-field>

        <v-text-field 
          v-model="addVehicleForm.mileage" 
          label="Kilométrage actuel" 
          type="number" 
          min="0"
          variant="outlined" density="comfortable" rounded="lg" prepend-inner-icon="mdi-speedometer" color="blue-darken-2" suffix="km"></v-text-field>
      </v-card-text>

      <v-divider></v-divider>
      <v-card-actions class="pa-4 ga-2">
        <v-btn variant="outlined" rounded="lg" color="grey" @click="$emit('show-register-form')">Annuler</v-btn>
        <v-spacer></v-spacer>
        <v-btn 
          variant="flat" 
          color="blue-darken-3" 
          rounded="lg" 
          prepend-icon="mdi-check" 
          class="font-weight-bold" 
          @click="addDialog = false; addVehicle()">
          Enregistrer
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { useBrandStore } from '@/stores/brand'
import { useVehicleStore } from '@/stores/vehicleStore'
import { reactive, ref, onMounted } from 'vue'

const addDialog = defineModel({ default: false })
const brandStore = useBrandStore()
const vehicleStore = useVehicleStore()

const currentYear = new Date().getFullYear()
const years = Array.from({ length: currentYear - 1970 + 1 }, (_, i) => currentYear - i)

const addVehicleForm = reactive({
  licensePlate: '',
  brand: null,
  model: '',
  year: null,
  fuel: '',
  color: '',
  vin: '',
  mileage: null
})

function addVehicle(){
  vehicleStore.addVehicle(
    addVehicleForm.brand,
    addVehicleForm.model,
    addVehicleForm.year,
    addVehicleForm.mileage,
    addVehicleForm.licensePlate
  )
}

const selectedBrand = ref('')
const loadingBrands = ref(false)
onMounted(async() => {
  loadingBrands.value = true
  await brandStore.getAllBrands()
    .then((response) => {
      console.log('Marques de véhicules :', brandStore.brands)
    })
    .catch((e) => {
      console.error('Erreur lors du chargement des marques :', e)
    })
    .finally(() => loadingBrands.value = false)
})
</script>