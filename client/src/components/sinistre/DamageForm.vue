<style scoped>
.upload-zone {
    border: 2px dashed #90caf9;
    border-radius: 12px;
    padding: 28px;
    text-align: center;
    cursor: pointer;
    transition: all .2s;
}
.upload-zone:hover { border-color: #1565c0; background: #202325; }

</style>

<template>
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
            <v-autocomplete
                label="Pièces endommagées *"
                variant="outlined"
                density="comfortable"
                prepend-inner-icon="mdi-car-wrench"
                :items="sinistreStore.damagedPieces"
                item-title="label"
                item-value="value"
                v-model="sinistreFormStore.damagedPieces"
                multiple
                chips
                closable-chips
                color="blue-darken-2"
            />
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
</template>

<script setup>
import { onMounted, ref, computed, watch } from 'vue'

import { useStepperBarStore } from '@/stores/stepperBarStore'
import { useSinistreStore } from '@/stores/sinistreStore'
import { useSinistreFormStore } from '@/stores/sinistreFormStore'

const sinistreFormStore = useSinistreFormStore()
const sinistreStore = useSinistreStore()

const isLoadingDamagedPieces = ref(false)
onMounted(async() => {
    isLoadingDamagedPieces.value = true
    await sinistreStore.getDamagedPieces()
    .then((response) => {
      console.log('Pieces endommagées :', response)
    })
    .catch((e) => {
      console.error('Erreur lors du chargement des pieces endommagées :', e)
    })
    .finally(() => {
        isLoadingDamagedPieces.value = false
    })
})
</script>