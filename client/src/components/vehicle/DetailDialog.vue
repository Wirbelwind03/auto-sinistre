  <style scoped>
  * { font-family: 'Nunito', sans-serif !important; }

  .plate-badge {
    font-family: 'Nunito', monospace !important;
    font-weight: 900 !important;
    letter-spacing: .1em;
    font-size: .85rem;
  }

.car-visual {
    aspect-ratio: 16 / 9;
    background: #e3f2fd;
    border-radius: 12px 12px 0 0;
    overflow: hidden;
}

  .car-visual-empty {
  aspect-ratio: 16 / 9;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

  .car-visual::after {
    content: '';
    position: absolute;
    bottom: 0; left: 0; right: 0;
    height: 30px;
    background: linear-gradient(to top, rgba(21,101,192,.08), transparent);
  }

  @keyframes fadeUp {
    from { opacity: 0; transform: translateY(16px); }
    to { opacity: 1; transform: translateY(0); }
  }

  .detail-sheet {
    max-height: 90vh;
    overflow-y: auto;
  }
</style>
  
  <template>
    <v-dialog v-model="detailDialog" max-width="560">
        <v-card v-if="vehicle" rounded="xl">
        <div class="car-visual">
            <v-img
                v-if="vehicle.photoUrl"
                :src="vehicle.photoUrl"
                cover
            >
                <template v-slot:error>
                <div class="car-visual-empty">
                    <v-icon size="40" color="blue-lighten-3">mdi-image-broken-variant</v-icon>
                </div>
                </template>
            </v-img>

            <div v-else class="car-visual-empty">
                <v-icon size="40" color="blue-lighten-3">mdi-camera-plus-outline</v-icon>
                <span class="text-caption text-blue-lighten-3 mt-1">Aucune photo</span>
            </div>
            <v-btn
            icon="mdi-close"
            variant="flat"
            color="rgba(0,0,0,.3)"
            size="small"
            style="position:absolute;top:12px;right:12px"
            @click="detailDialog = false"
            ></v-btn>
        </div>

        <v-card-text class="pa-6 detail-sheet">
            <div class="d-flex align-start justify-space-between mb-4">
            <div>
                <div class="text-h6 font-weight-black text-grey-lighten-1">{{ vehicle.brand.name }} {{ vehicle.model }}</div>
                <div class="text-body-2 text-medium-emphasis">{{ vehicle.year }} · {{ vehicle.color }}</div>
            </div>
            <v-chip class="plate-badge" color="blue-darken-4" variant="flat">{{ vehicle.licensePlate }}</v-chip>
            </div>

            <!-- Tabs -->
            <v-tabs v-model="detailTab" color="blue-darken-2" class="mb-4">
            <v-tab value="infos" class="text-uppercase" prepend-icon="mdi-information">Infos</v-tab>
            <v-tab value="sinistres" class="text-uppercase" prepend-icon="mdi-alert-circle">Sinistres</v-tab>
            </v-tabs>

            <v-tabs-window v-model="detailTab">
            <!-- INFOS TAB -->
            <v-tabs-window-item value="infos">
                <v-list density="compact" rounded="lg">
                <v-list-item v-for="info in vehicleInfos" :key="info.label" :prepend-icon="info.icon">
                    <v-list-item-title class="text-caption text-medium-emphasis">{{ info.label }}</v-list-item-title>
                    <template v-slot:append>
                    <span class="text-body-2 font-weight-bold">{{ info.val }}</span>
                    </template>
                </v-list-item>
                </v-list>
            </v-tabs-window-item>

            <!-- SINISTRES TAB -->
            <v-tabs-window-item value="sinistres">
                <v-timeline density="compact" side="end" truncate-line="both">
                <v-timeline-item
                    v-for="s in vehicleSinistres"
                    :key="s.id"
                    :dot-color="s.color"
                    size="small"
                >
                    <div class="d-flex align-start justify-space-between">
                    <div>
                        <div class="text-body-2 font-weight-bold">{{ s.type }}</div>
                        <div class="text-caption text-medium-emphasis">{{ s.date }}</div>
                    </div>
                    <div class="text-right">
                        <v-chip :color="s.color" size="x-small" variant="flat" class="font-weight-bold">{{ s.statut }}</v-chip>
                        <div class="text-caption font-weight-bold mt-1">{{ s.montant }}</div>
                    </div>
                    </div>
                </v-timeline-item>
                </v-timeline>
            </v-tabs-window-item>
            </v-tabs-window>
        </v-card-text>

        <v-divider></v-divider>
        <v-card-actions class="pa-4 ga-2">
            <v-btn variant="outlined" rounded="lg" color="grey" @click="detailDialog = false" class="text-uppercase">Fermer</v-btn>
            <v-spacer></v-spacer>
            <v-btn variant="flat" color="blue-darken-3" rounded="lg" prepend-icon="mdi-alert-plus" class="font-weight-bold text-uppercase" @click="detailDialog = false; declarationSnack = true">
            Déclarer un sinistre
            </v-btn>
        </v-card-actions>
        </v-card>
    </v-dialog>
  </template>

<script setup>
import { computed, ref } from 'vue'

const props = defineProps({
  vehicle: {
    type: Object,
    required: true
  }
})

const detailTab = ref('infos')

const vehicleInfos = computed(() => props.vehicle ? [
    { label: 'Marque / Modèle', val: `${props.vehicle.brand.name} ${props.vehicle.model}`, icon: 'mdi-car' },
    { label: 'Année', val: props.vehicle.year, icon: 'mdi-calendar' },
    { label: 'Carburant', val: props.vehicle.fuelType, icon: 'mdi-gas-station' },
    { label: 'Kilométrage', val: `${props.vehicle.mileage.toLocaleString('fr')} km`, icon: 'mdi-speedometer' },
    { label: 'Immatriculation', val: props.vehicle.licensePlate, icon: 'mdi-card-text' },
] : [])

const detailDialog = defineModel({ default: false })
</script>