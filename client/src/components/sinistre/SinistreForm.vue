<style scoped>
  :root {
    --v-font-family: 'Nunito', sans-serif !important;
  }
  * { font-family: 'Nunito', sans-serif !important; }

  .type-card { cursor: pointer; transition: all .2s !important; border: 2px solid transparent !important; }
  .type-card:hover { border-color: #1565c0 !important; transform: translateY(-2px); }
  .type-card.selected { border-color: #1565c0 !important; background: #e3f2fd !important; }
</style>

<template>
    <v-card rounded="xl" class="mb-5" elevation="1">
        <v-card-title class="d-flex align-center ga-3 pa-5 pb-3">
            <v-avatar color="orange-darken-1" rounded="lg" size="36">
            <v-icon size="20">mdi-lightning-bolt</v-icon>
            </v-avatar>
            <div>
            <div class="text-subtitle-1 font-weight-bold">Type de sinistre</div>
            <div class="text-caption text-medium-emphasis">Nature de l'incident</div>
            </div>
        </v-card-title>
        <v-divider></v-divider>
        <v-card-text class="pa-5">
            <v-row v-if="isLoadingTypes">
                <v-col class="d-flex justify-center align-center py-8">
                    <v-progress-circular indeterminate></v-progress-circular>
                </v-col>
            </v-row>

            <v-row>
                <v-col v-for="type in sinistreStore.types" :key="type.value" cols="4">
                    <v-card
                    rounded="lg"
                    :class="['type-card', selectedType === type.value ? 'selected' : '']"
                    elevation="0"
                    border
                    @click="selectedType = type.value"
                    >
                    <v-card-text class="text-center pa-4">
                        <v-icon :color="type.color" size="32" class="mb-2">{{ type.icon }}</v-icon>
                        <div class="text-body-2 font-weight-bold">{{ type.label }}</div>
                        <div class="text-caption text-medium-emphasis">{{ type.description }}</div>
                    </v-card-text>
                    </v-card>
                </v-col>
            </v-row>

            <div class="mt-5">
            <div class="text-caption font-weight-bold text-medium-emphasis text-uppercase mb-3">Sévérité estimée *</div>
            <v-btn-toggle v-model="selectedSeverity" rounded="lg" color="white" divided>
                <v-btn value="FAIBLE" color="success" variant="outlined" class="font-weight-bold">
                <v-icon start>mdi-circle-small</v-icon> Faible
                </v-btn>
                <v-btn value="MOYENNE" color="warning" variant="outlined" class="font-weight-bold">
                <v-icon start>mdi-circle-medium</v-icon> Moyenne
                </v-btn>
                <v-btn value="ELEVEE" color="error" variant="outlined" class="font-weight-bold">
                <v-icon start>mdi-circle</v-icon> Élevée
                </v-btn>
            </v-btn-toggle>
            </div>
        </v-card-text>
    </v-card>
</template>

<script setup>
import { onMounted, ref, computed, watch } from 'vue'

import { useStepperBarStore } from '@/stores/stepperBarStore'
import { useSinistreStore } from '@/stores/sinistreStore'

const sinistreStore = useSinistreStore()

const isLoadingTypes = ref(false)
onMounted(async() => {
    isLoadingTypes.value = true
    await sinistreStore.getTypes()
    .then((response) => {
      console.log('Types :', sinistreStore.types)
    })
    .catch((e) => {
      console.error('Erreur lors du chargement des types :', e)
    })
    .finally(() => {
        isLoadingTypes.value = false
    })
})

const stepperBarStore = useStepperBarStore()

const selectedType = ref('')
const selectedSeverity = ref('')
watch([selectedType, selectedSeverity], ([type, severity]) => {
  if (type != '' && severity != ''){
    if (stepperBarStore.currentStep < 3){
      stepperBarStore.currentStep = 3
    }
  }
  else {
    stepperBarStore.currentStep = 2
  }
})
</script>