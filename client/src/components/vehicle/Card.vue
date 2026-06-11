<style scoped>
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
</style>

<template>
    <v-card class="vehicle-card" rounded="xl" elevation="1" border @click="emit('open-detail', vehicle)">
        <!-- Car visual header -->
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
        </div>

        <v-card-text class="pa-4">
            <!-- Plate + model -->
            <div class="d-flex align-start justify-space-between mb-2">
            <div>
                <div class="text-body-1 font-weight-black text-blue-darken-1">{{ vehicle.brand }} {{ vehicle.model }}</div>
                <div class="text-caption text-medium-emphasis">{{ vehicle.year }}</div>
            </div>
            <v-chip class="plate-badge" color="blue-darken-4" variant="flat" size="small">
                {{ vehicle.licensePlate }}
            </v-chip>
            </div>

            <v-divider class="my-3"></v-divider>

            <!-- Infos -->
            <div class="d-flex flex-column ga-1 mb-3">
            <div class="d-flex align-center ga-2">
                <v-icon size="14" color="grey">mdi-speedometer</v-icon>
                <span class="text-caption text-medium-emphasis">{{ vehicle.mileage.toLocaleString('fr') }} km</span>
            </div>
            </div>

            <!-- Actions -->
            <div class="d-flex ga-2 align-center">
            <v-btn
                variant="flat"
                color="blue-darken-3"
                size="small"
                rounded="lg"
                class="flex-grow-1 font-weight-bold text-uppercase"
                prepend-icon="mdi-alert-plus"
                @click.stop="emit('open-declaration', vehicle)"
            >
                Déclarer
            </v-btn>
            <v-btn
                variant="outlined"
                color="blue-darken-2"
                size="small"
                rounded="lg"
                icon="mdi-eye"
                @click.stop="emit('open-detail', vehicle)"
            ></v-btn>
            <!--<v-btn
                variant="outlined"
                color="grey"
                size="small"
                rounded="lg"
                icon="mdi-pencil"
                @click.stop="emit('open-edit', vehicle)"
            ></v-btn>-->
            </div>
        </v-card-text>
    </v-card>
</template>

<script setup>
const props = defineProps({
  vehicle: {
    type: Object,
    required: true
  }
})

const emit = defineEmits(['open-detail', 'open-declaration', 'open-edit'])
</script>