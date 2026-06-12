<template>
    <v-card rounded="xl" class="mb-5" elevation="1">
        <v-card-title class="d-flex align-center ga-3 pa-5 pb-3">
        <v-avatar color="purple-darken-1" rounded="lg" size="36">
            <v-icon size="20">mdi-map-marker</v-icon>
        </v-avatar>
        <div>
            <div class="text-subtitle-1 font-weight-bold">Date, heure & lieu</div>
            <div class="text-caption text-medium-emphasis">Circonstances de l'incident</div>
        </div>
        </v-card-title>
        <v-divider></v-divider>
        <v-card-text class="pa-5">
        <v-row>
            <v-col cols="6">
            <v-text-field
                v-model="sinistreFormStore.date"
                label="Date du sinistre *"
                type="date"
                variant="outlined"
                density="comfortable"
                prepend-inner-icon="mdi-calendar"
                color="blue-darken-2"
            ></v-text-field>
            </v-col>
            <v-col cols="6">
            <v-text-field
                v-model="sinistreFormStore.hour"
                label="Heure approximative"
                type="time"
                variant="outlined"
                density="comfortable"
                prepend-inner-icon="mdi-clock-outline"
                color="blue-darken-2"
            ></v-text-field>
            </v-col>
        </v-row>

        <v-autocomplete
            v-model="sinistreFormStore.location"
            v-model:search="searchQuery"
            :items="suggestions"
            item-title="label"
            item-value="value"
            label="Adresse / lieu de l'incident *"
            variant="outlined"
            density="comfortable"
            prepend-inner-icon="mdi-map-search"
            color="blue-darken-2"
            class="mb-4"
            no-filter
            clearable
            return-object
            :loading="loading"
            no-data-text="Aucune adresse trouvée"
            @update:search="onSearch"
        />

        <v-textarea
            label="Description des circonstances *"
            variant="outlined"
            density="comfortable"
            prepend-inner-icon="mdi-text"
            rows="3"
            color="blue-darken-2"
            class="mb-4"
        ></v-textarea>

        <v-row>
            <v-col cols="6">
            <v-select
                label="Conditions météo"
                variant="outlined"
                density="comfortable"
                prepend-inner-icon="mdi-weather-partly-cloudy"
                :items="['☀️ Dégagé', '🌧️ Pluie', '🌨️ Neige / verglas', '🌫️ Brouillard']"
                color="blue-darken-2"
            ></v-select>
            </v-col>
            <v-col cols="6">
            <v-select
                label="Tiers impliqué ? *"
                variant="outlined"
                density="comfortable"
                prepend-inner-icon="mdi-account-alert"
                :items="['Oui — coordonnées connues', 'Non', 'Délit de fuite']"
                color="blue-darken-2"
            ></v-select>
            </v-col>
        </v-row>
        </v-card-text>
    </v-card>
</template>

<script setup>
import { onMounted, ref, computed, watch } from 'vue'

import { useAddressSearch } from '@/composables/useAddressSearch'

import { useStepperBarStore } from '@/stores/stepperBarStore'
import { useSinistreStore } from '@/stores/sinistreStore'
import { useSinistreFormStore } from '@/stores/sinistreFormStore'

const stepperBarStore = useStepperBarStore()
const sinistreStore = useSinistreStore()
const sinistreFormStore = useSinistreFormStore()

const searchQuery = ref('')
const { suggestions, loading, onSearch } = useAddressSearch()

watch([() => sinistreFormStore.date, () => sinistreFormStore.hour, () => sinistreFormStore.location], () => {
  if (sinistreFormStore.date && sinistreFormStore.hour && sinistreFormStore.location) {
    if (stepperBarStore.currentStep < 4){
        stepperBarStore.currentStep = 4
    } 
  }
  else{
    stepperBarStore.currentStep = 3
  }
})
</script>