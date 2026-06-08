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
          label="Plaque d'immatriculation *"
          variant="outlined"
          density="comfortable"
          rounded="lg"
          prepend-inner-icon="mdi-card-account-details"
          placeholder="AB-421-CD"
          class="mb-3"
          color="blue-darken-2"
          hint="Format français XX-000-XX"
          persistent-hint
          style="font-weight:800;letter-spacing:.1em;text-transform:uppercase"
        ></v-text-field>

        <v-row>
          <v-col cols="6">
            <v-text-field label="Marque *" variant="outlined" density="comfortable" rounded="lg" color="blue-darken-2" model-value="Renault"></v-text-field>
          </v-col>
          <v-col cols="6">
            <v-text-field label="Modèle *" variant="outlined" density="comfortable" rounded="lg" color="blue-darken-2" model-value="Mégane"></v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="4">
            <v-text-field label="Année *" type="number" variant="outlined" density="comfortable" rounded="lg" color="blue-darken-2" model-value="2020"></v-text-field>
          </v-col>
          <v-col cols="4">
            <v-select label="Carburant *" :items="['Essence', 'Diesel', 'Électrique', 'Hybride', 'GPL']" model-value="Diesel" variant="outlined" density="comfortable" rounded="lg" color="blue-darken-2"></v-select>
          </v-col>
          <v-col cols="4">
            <v-text-field label="Couleur" variant="outlined" density="comfortable" rounded="lg" color="blue-darken-2" model-value="Blanc"></v-text-field>
          </v-col>
        </v-row>

        <v-text-field label="N° VIN (châssis)" variant="outlined" density="comfortable" rounded="lg" prepend-inner-icon="mdi-identifier" class="mb-3" color="blue-darken-2" hint="17 caractères — optionnel" persistent-hint></v-text-field>

        <v-select
          label="Contrat d'assurance associé *"
          :items="['GRP-2024-08841 — Tous risques', 'GRP-2023-05512 — Tiers étendu']"
          model-value="GRP-2024-08841 — Tous risques"
          variant="outlined"
          density="comfortable"
          rounded="lg"
          prepend-inner-icon="mdi-file-document"
          color="blue-darken-2"
          class="mb-3"
        ></v-select>

        <v-text-field label="Kilométrage actuel" type="number" variant="outlined" density="comfortable" rounded="lg" prepend-inner-icon="mdi-speedometer" color="blue-darken-2" model-value="48000" suffix="km"></v-text-field>
      </v-card-text>

      <v-divider></v-divider>
      <v-card-actions class="pa-4 ga-2">
        <v-btn variant="outlined" rounded="lg" color="grey" @click="$emit('show-register-form')">Annuler</v-btn>
        <v-spacer></v-spacer>
        <v-btn variant="flat" color="blue-darken-3" rounded="lg" prepend-icon="mdi-check" class="font-weight-bold" @click="addDialog = false; addSnack = true">
          Enregistrer
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import { reactive, ref } from 'vue'

const addDialog = ref(false)
</script>