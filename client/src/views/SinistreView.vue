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
</style>

<template>
     <!-- STEPPER BAR -->
      <v-sheet color="blue-darken-2" class="px-6 py-3">
        <div class="d-flex align-center ga-2">
          <template v-for="(step, i) in steps" :key="i">
            <div class="d-flex align-center ga-2">
              <v-avatar
                size="28"
                :class="i < currentStep ? 'step-done' : i === currentStep ? 'step-active' : 'step-idle'"
              >
                <v-icon v-if="i < currentStep" size="16">mdi-check</v-icon>
                <span v-else style="font-size:.8rem;font-weight:700">{{ i + 1 }}</span>
              </v-avatar>
              <span :class="i === currentStep ? 'text-white font-weight-bold' : 'text-blue-lighten-3'" style="font-size:.82rem">
                {{ step }}
              </span>
            </div>
            <v-icon v-if="i < steps.length - 1" color="blue-lighten-3" size="16">mdi-chevron-right</v-icon>
          </template>

          <v-spacer></v-spacer>

          <v-btn size="small" variant="outlined" color="white" class="mr-2" :disabled="currentStep === 0" @click="currentStep--">
            Précédent
          </v-btn>
          <v-btn size="small" color="yellow-accent-3" variant="flat" class="text-black font-weight-bold" :disabled="currentStep === steps.length - 1" @click="currentStep++">
            Suivant
          </v-btn>
        </div>
      </v-sheet>

      <v-container fluid class="pa-6">
        <v-row>
          <!-- LEFT COL -->
          <v-col cols="12" md="8">

            <!-- SECTION 1: VÉHICULE -->
            <v-card rounded="xl" class="mb-5" elevation="1">
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
                    <v-text-field
                      v-model="plate"
                      label="Plaque d'immatriculation *"
                      variant="outlined"
                      density="comfortable"
                      prepend-inner-icon="mdi-card-account-details"
                      placeholder="AB-421-CD"
                      :style="{fontFamily: 'Nunito', fontWeight: 700, letterSpacing: '0.1em', textTransform: 'uppercase'}"
                      hint="Recherche automatique dans la base assurés"
                      persistent-hint
                      color="blue-darken-2"
                    ></v-text-field>
                  </v-col>
                </v-row>

                <!-- Vehicle found -->
                <v-card rounded="lg" color="green-lighten-5" class="vehicle-found-card mt-1" elevation="0">
                  <v-card-text class="d-flex align-center ga-3 pa-4">
                    <v-avatar color="green-lighten-3" rounded="lg" size="44">
                      <v-icon color="green-darken-2" size="24">mdi-car-hatchback</v-icon>
                    </v-avatar>
                    <div class="flex-grow-1">
                      <div class="font-weight-bold text-body-2">Peugeot 308 SW · Gris Platinium</div>
                      <div class="text-caption text-medium-emphasis">2021 · Diesel · Jean-Pierre Moulin · Contrat actif depuis jan. 2022</div>
                    </div>
                    <v-chip color="success" size="small" prepend-icon="mdi-check-circle" variant="flat">Véhicule trouvé</v-chip>
                  </v-card-text>
                </v-card>
              </v-card-text>
            </v-card>

            <!-- SECTION 2: TYPE -->
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
                <v-row>
                  <v-col v-for="type in sinisterTypes" :key="type.value" cols="4">
                    <v-card
                      rounded="lg"
                      :class="['type-card', selectedType === type.value ? 'selected' : '']"
                      elevation="0"
                      border
                      @click="selectedType = type.value"
                    >
                      <v-card-text class="text-center pa-4">
                        <v-icon :color="type.color" size="32" class="mb-2">{{ type.icon }}</v-icon>
                        <div class="text-body-2 font-weight-bold">{{ type.name }}</div>
                        <div class="text-caption text-medium-emphasis">{{ type.desc }}</div>
                      </v-card-text>
                    </v-card>
                  </v-col>
                </v-row>

                <div class="mt-5">
                  <div class="text-caption font-weight-bold text-medium-emphasis text-uppercase mb-3">Sévérité estimée *</div>
                  <v-btn-toggle v-model="severity" mandatory rounded="lg" color="white" divided>
                    <v-btn value="low" color="success" variant="outlined" class="font-weight-bold">
                      <v-icon start>mdi-circle-small</v-icon> Faible
                    </v-btn>
                    <v-btn value="med" color="warning" variant="flat" class="font-weight-bold">
                      <v-icon start>mdi-circle-medium</v-icon> Moyenne
                    </v-btn>
                    <v-btn value="high" color="error" variant="outlined" class="font-weight-bold">
                      <v-icon start>mdi-circle</v-icon> Élevée
                    </v-btn>
                  </v-btn-toggle>
                </div>
              </v-card-text>
            </v-card>

            <!-- SECTION 3: DATE & LIEU -->
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
                      model-value="☀️ Dégagé"
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
                      model-value="Oui — coordonnées connues"
                      color="blue-darken-2"
                    ></v-select>
                  </v-col>
                </v-row>
              </v-card-text>
            </v-card>

            <!-- SECTION 4: DOMMAGES & UPLOAD -->
            <v-card rounded="xl" class="mb-5" elevation="1">
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
import { useAuthStore } from '@/stores/auth'
import { onMounted, ref } from 'vue'

import { useAppBarStore } from '@/stores/appBarStore.js'

const appBarStore = useAppBarStore()

const steps = ['Véhicule', 'Sinistre', 'Circonstances', 'Documents', 'Récap']

const sinisterTypes = [
    { value: 'accident', name: 'Accident', desc: 'Collision, choc', icon: 'mdi-car-crash', color: 'red-darken-1' },
    { value: 'vol', name: 'Vol', desc: 'Total ou partiel', icon: 'mdi-key-variant', color: 'orange-darken-2' },
    { value: 'bris', name: 'Bris de glace', desc: 'Pare-brise, vitres', icon: 'mdi-car-windshield', color: 'blue-darken-1' },
    { value: 'incendie', name: 'Incendie', desc: 'Feu, explosion', icon: 'mdi-fire', color: 'deep-orange-darken-2' },
    { value: 'catnat', name: 'Catastrophe nat.', desc: 'Inondation, grêle', icon: 'mdi-weather-hurricane', color: 'teal-darken-1' },
    { value: 'autre', name: 'Autre', desc: 'Vandalisme, etc.', icon: 'mdi-dots-horizontal-circle', color: 'grey-darken-1' },
]

const selectedType = ref('accident')
const severity = ref('med')
const currentStep = ref(0)

onMounted(async() => {
    appBarStore.setBar('Déclarer un sinistre')
})
</script>