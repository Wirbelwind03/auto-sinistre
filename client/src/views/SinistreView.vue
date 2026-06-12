<style scoped>
  :root {
    --v-font-family: 'Nunito', sans-serif !important;
  }
  * { font-family: 'Nunito', sans-serif !important; }

  .sev-chip { cursor: pointer; }
  .summary-card { position: sticky; top: 80px; }

  .step-active { background: #1565c0 !important; color: white !important; }
  .step-done { background: #43a047 !important; color: white !important; }
  .step-idle { background: #e0e0e0 !important; color: #9e9e9e !important; }

  .vehicle-found-card { border-left: 4px solid #43a047 !important; }
  .kpi-chip { font-weight: 700 !important; font-size: .95rem !important; }

.section-enter {
  opacity: 0;
  transform: translateY(24px);
  transition: opacity 0.45s ease, transform 0.45s ease;
}

.section-enter-active,
.section-visible {
  opacity: 1;
  transform: translateY(0);
}

.fade-up-enter-active {
  transition: opacity 0.45s ease, transform 0.45s ease;
}
.fade-up-enter-from {
  opacity: 0;
  transform: translateY(28px);
}
.fade-up-enter-to {
  opacity: 1;
  transform: translateY(0);
}
</style>

<template>
     <!-- STEPPER BAR -->
      <stepper-bar :steps="steps"></stepper-bar>

      <v-container fluid class="pa-6">
        <v-row>
          <!-- LEFT COL -->
          <v-col cols="12" md="8">

            <!-- SECTION 1: VÉHICULE -->
            <Transition name="fade-up">
              <vehicle-form v-if="stepperBarStore.currentStep >= 1" id="section-1"/>
            </Transition>


            <!-- SECTION 2: TYPE -->
            <Transition name="fade-up">
              <sinistre-form v-if="stepperBarStore.currentStep >= 2" id="section-2"/>
            </Transition>


            <!-- SECTION 3: DATE & LIEU -->
             <Transition name="fade-up">
              <circumstance-form v-if="stepperBarStore.currentStep >= 3" id="section-3"/>
             </Transition>
             
            <!-- SECTION 4: DOMMAGES & UPLOAD -->
             <Transition name ="fade-up">
              <damage-form id="section-4" />
             </Transition>
          </v-col>

          <!-- RIGHT COL: RÉSUMÉ -->
          <v-col cols="12" md="4">
            <v-card rounded="xl" elevation="2" class="summary-card">
              <v-card-title class="d-flex align-center ga-2 pa-5 pb-3">
                <v-icon color="blue-darken-2">mdi-clipboard-list</v-icon>
                <span class="font-weight-bold">Récapitulatif</span>
              </v-card-title>
              <v-divider></v-divider>
              <v-list density="compact" class="pa-2">
                <v-list-item v-for="row in summary" :key="row.key">
                  <template v-slot:prepend>
                    <v-icon :color="row.color || 'grey'" size="18">{{ row.icon }}</v-icon>
                  </template>
                  <v-list-item-title class="text-caption text-medium-emphasis">{{ row.key }}</v-list-item-title>
                  <template v-slot:append>
                    <span :class="['text-body-2', 'font-weight-bold', row.valClass || '']">{{ row.val }}</span>
                  </template>
                </v-list-item>
              </v-list>

              <v-divider></v-divider>

              <v-card-text class="pa-4">
                <div class="d-flex justify-space-between align-center mb-3">
                  <span class="text-body-2 text-medium-emphasis">Coût estimé</span>
                  <v-chip color="blue-darken-2" variant="flat" class="kpi-chip font-weight-black text-white" size="large">
                  </v-chip>
                </div>

                <v-alert type="info" variant="tonal" density="compact" rounded="lg" class="mb-4" icon="mdi-clock-fast">
                  <span class="text-caption">Traitement sous <strong>48–72h</strong> ouvrées après soumission.</span>
                </v-alert>

                <v-btn
                  color="blue-darken-3"
                  variant="flat"
                  block
                  rounded="lg"
                  append-icon="mdi-send"
                  class="mb-2 font-weight-bold"
                  @click="submitted = true"
                >
                  Soumettre la déclaration
                </v-btn>
                <v-btn
                  variant="outlined"
                  color="blue-darken-2"
                  block
                  rounded="lg"
                  prepend-icon="mdi-content-save"
                  class="font-weight-bold"
                >
                  Sauvegarder brouillon
                </v-btn>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
</template>

<script setup>
import { onMounted, ref, computed, watch, nextTick } from 'vue'

import { useAppBarStore } from '@/stores/appBarStore.js'
import { useStepperBarStore } from '@/stores/stepperBarStore'
import { useSinistreStore } from '@/stores/sinistreStore'
import { useSinistreFormStore } from '@/stores/sinistreFormStore'

import StepperBar from '@/components/StepperBar.vue'
import VehicleForm from '@/components/sinistre/VehicleForm.vue'
import SinistreForm from '@/components/sinistre/SinistreForm.vue'
import CircumstanceForm from '@/components/sinistre/CircumstanceForm.vue'
import DamageForm from '@/components/sinistre/DamageForm.vue'

const appBarStore = useAppBarStore()
const stepperBarStore = useStepperBarStore()

const sinistreStore = useSinistreStore()
const sinistreFormStore = useSinistreFormStore()

const steps = ['Véhicule', 'Sinistre', 'Circonstances', 'Documents', 'Récap']

const summary = computed(() => [
  {
    key: 'Plaque',
    val: sinistreFormStore.licensePlate || '—',
    icon: 'mdi-card-account-details'
  },
  {
    key: 'Véhicule',
    val: sinistreFormStore.vehicle 
    ? `${sinistreFormStore.vehicle.brand} ${sinistreFormStore.vehicle.model}` 
    : '—',
    icon: 'mdi-car'
  },
  {
    key: 'Type',
    val: sinistreStore.types
      ? (sinistreStore.types.find(e => e.value === sinistreFormStore.type))?.label || '—'
      : '—',
    icon: 'mdi-alert',
    color: 'red'
  },
  {
    key: 'Sévérité',
    val: sinistreFormStore.severityLabel.label || '—',
    icon: 'mdi-gauge',
    color: sinistreFormStore.severityLabel.color,
    valClass: sinistreFormStore.severityLabel.valClass
  },
  {
    key: 'Date',
    val: sinistreFormStore.date || '—',
    icon: 'mdi-calendar'
  },
  {
    key: 'Heure',
    val: sinistreFormStore.hour || '—',
    icon: 'mdi-clock-outline'
  },
  {
    key: 'Lieu',
    val: sinistreFormStore.location
    ? sinistreFormStore.location.label : 
    '—',
    icon: 'mdi-map-marker'
  }
])

onMounted(async() => {
    appBarStore.setBar('Déclarer un sinistre')
})

watch(() => stepperBarStore.currentStep, (step) => {
  nextTick(() => {
    document.getElementById(`section-${step}`)
      ?.scrollIntoView({ behavior: 'smooth', block: 'start' })
  })
})
</script>