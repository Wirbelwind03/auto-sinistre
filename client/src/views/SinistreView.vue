<style scoped>
  :root {
    --v-font-family: 'Nunito', sans-serif !important;
  }
  * { font-family: 'Nunito', sans-serif !important; }

  .nav-rail { background: #1a237e !important; }
  .top-app-bar { background: #1565c0 !important; }

  .type-card { cursor: pointer; transition: all .2s !important; border: 2px solid transparent !important; }
  .type-card:hover { border-color: #1565c0 !important; transform: translateY(-2px); }
  .type-card.selected { border-color: #1565c0 !important; background: #e3f2fd !important; }

  .sev-chip { cursor: pointer; }
  .summary-card { position: sticky; top: 80px; }

  .step-active { background: #1565c0 !important; color: white !important; }
  .step-done { background: #43a047 !important; color: white !important; }
  .step-idle { background: #e0e0e0 !important; color: #9e9e9e !important; }

  .upload-zone {
    border: 2px dashed #90caf9;
    border-radius: 12px;
    padding: 28px;
    text-align: center;
    cursor: pointer;
    background: #f8fbff;
    transition: all .2s;
  }
  .upload-zone:hover { border-color: #1565c0; background: #e3f2fd; }

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
              <vehicle v-if="stepperBarStore.currentStep >= 1" id="section-1"/>
            </Transition>


            <!-- SECTION 2: TYPE -->
            <Transition name="fade-up">
              <sinistre-form v-if="stepperBarStore.currentStep >= 2" id="section-2"/>
            </Transition>


            <!-- SECTION 3: DATE & LIEU -->
            <v-card v-if="stepperBarStore.currentStep >= 3" id="section-3" rounded="xl" class="mb-5" elevation="1">
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
                      label="Heure approximative"
                      type="time"
                      variant="outlined"
                      density="comfortable"
                      prepend-inner-icon="mdi-clock-outline"
                      color="blue-darken-2"
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-text-field
                  label="Adresse / lieu de l'incident *"
                  variant="outlined"
                  density="comfortable"
                  prepend-inner-icon="mdi-map-search"
                  persistent-hint
                  class="mb-4"
                  color="blue-darken-2"
                ></v-text-field>

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
                      label="Tiers impliqué ?"
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

            <!-- SECTION 4: DOMMAGES & UPLOAD -->
            <v-card v-if="stepperBarStore.currentStep >= 4" id="section-4" rounded="xl" class="mb-5" elevation="1">
              <v-card-title class="d-flex align-center ga-3 pa-5 pb-3">
                <v-avatar color="red-darken-1" rounded="lg" size="36">
                  <v-icon size="20">mdi-wrench</v-icon>
                </v-avatar>
                <div>
                  <div class="text-subtitle-1 font-weight-bold">Dommages & Documents</div>
                  <div class="text-caption text-medium-emphasis">Estimation et pièces justificatives</div>
                </div>
              </v-card-title>
              <v-divider></v-divider>
              <v-card-text class="pa-5">
                <v-row>
                  <v-col cols="8">
                    <v-select
                      label="Pièces endommagées *"
                      variant="outlined"
                      density="comfortable"
                      prepend-inner-icon="mdi-car-wrench"
                      :items="['Aile avant droite', 'Pare-choc avant', 'Carrosserie latérale', 'Moteur / mécanique']"
                      color="blue-darken-2"
                    ></v-select>
                  </v-col>
                  <v-col cols="4">
                    <v-text-field
                      label="Coût estimé (€)"
                      type="number"
                      variant="outlined"
                      density="comfortable"
                      prepend-inner-icon="mdi-currency-eur"
                      color="blue-darken-2"
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-textarea
                  label="Notes techniques"
                  variant="outlined"
                  density="comfortable"
                  rows="2"
                  prepend-inner-icon="mdi-note-text"
                  color="blue-darken-2"
                  class="mb-4"
                ></v-textarea>

                <!-- Upload zone -->
                <div class="upload-zone mb-3">
                  <v-icon color="blue-darken-2" size="36" class="mb-2">mdi-cloud-upload-outline</v-icon>
                  <div class="text-body-2 font-weight-bold text-blue-darken-2">Glissez vos fichiers ici</div>
                  <div class="text-caption text-medium-emphasis">ou cliquez pour parcourir · JPG, PNG, PDF · max 10 Mo</div>
                </div>

                <div class="d-flex flex-wrap ga-2">
                  <v-chip
                    v-for="f in uploadedFiles"
                    :key="f"
                    closable
                    prepend-icon="mdi-file"
                    color="blue-lighten-4"
                    variant="flat"
                    size="small"
                  >{{ f }}</v-chip>
                </div>
              </v-card-text>
            </v-card>
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
                    € 4 200
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

import StepperBar from '@/components/StepperBar.vue'
import Vehicle from '@/components/sinistre/Vehicle.vue'
import SinistreForm from '@/components/sinistre/SinistreForm.vue'

const appBarStore = useAppBarStore()
const stepperBarStore = useStepperBarStore()

const steps = ['Véhicule', 'Sinistre', 'Circonstances', 'Documents', 'Récap']

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