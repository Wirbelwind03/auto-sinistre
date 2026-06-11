<template>
  <v-card rounded="xl" elevation="1">

    <!-- Header -->
    <v-row no-gutters class="px-4 py-3" style="border-radius:12px 12px 0 0; border-bottom:1px">
      <v-col cols="5">
        <span class="text-caption font-weight-bold text-medium-emphasis text-uppercase" style="letter-spacing:.08em">Véhicule</span>
      </v-col>
      <v-col cols="3">
        <span class="text-caption font-weight-bold text-medium-emphasis text-uppercase" style="letter-spacing:.08em">Plaque</span>
      </v-col>
      <v-col cols="4" class="text-right">
        <span class="text-caption font-weight-bold text-medium-emphasis text-uppercase" style="letter-spacing:.08em">Actions</span>
      </v-col>
    </v-row>

    <v-divider></v-divider>

    <template v-for="(vehicle, i) in vehicles" :key="vehicle.id">
      <v-row
        no-gutters
        align="center"
        class="vehicle-row px-4 py-3"
        @click="emit('open-detail', vehicle)"
      >
        <!-- Véhicule -->
        <v-col cols="5">
          <div class="text-body-2 font-weight-black">{{ vehicle.brand.name }} {{ vehicle.model }}</div>
          <div class="text-caption text-medium-emphasis">{{ vehicle.year }} · {{ vehicle.fuelType }} · {{ vehicle.color }}</div>
        </v-col>

        <!-- Plaque -->
        <v-col cols="3">
          <v-chip class="plate-badge" color="blue-darken-4" variant="flat" size="small">
            {{ vehicle.licensePlate }}
          </v-chip>
        </v-col>

        <!-- Actions -->
        <v-col cols="4">
          <div class="d-flex justify-end ga-1 align-center" @click.stop>
            <v-btn
              variant="flat"
              color="blue-darken-3"
              size="small"
              rounded="lg"
              prepend-icon="mdi-alert-plus"
              class="font-weight-bold"
              @click.stop="declarationSnack = true"
            >
              Déclarer
            </v-btn>
            <v-btn
              variant="outlined"
              color="grey"
              size="small"
              rounded="lg"
              icon="mdi-dots-vertical"
              @click.stop="openMenu($event, vehicle)"
            >
            </v-btn>
          </div>
        </v-col>
      </v-row>

      <v-divider v-if="i < vehicles.length - 1"></v-divider>
    </template>

    <v-divider></v-divider>

    <!-- Footer -->
    <v-row no-gutters class="px-4 py-3" style="border-radius:0 0 12px 12px;border-top:1px">
        <v-col>
        <span class="text-caption text-medium-emphasis">
            {{ vehicles.length }} véhicule{{ vehicles.length > 1 ? 's' : '' }} affiché{{ vehicles.length > 1 ? 's' : '' }}
        </span>
        </v-col>
    </v-row>
  </v-card>
</template>


<script setup>
defineProps({
  vehicles: {
    type: Array,
    required: true
  }
})

const emit = defineEmits(['open-detail', 'open-add-dialog'])
</script>