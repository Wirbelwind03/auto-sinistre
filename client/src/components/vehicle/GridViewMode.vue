<template>
    <v-row>
        <v-col 
            v-for="(vehicle, i) in vehicles"
            :key="vehicle.id"
            :style="`animation-delay:${i * .08}s`"
            cols="12" sm="6" md="4"
        >
            <Card 
                :vehicle="vehicle"
                @open-detail="emit('open-detail', vehicle)"
            />
        </v-col>


        <!-- Carte ajouter un véhicule -->
            <v-col cols="12" sm="6" md="4">
            <AddCard 
                @click="emit('open-add-dialog')"
            />
            </v-col>
    </v-row>
</template>

<script setup>
import Card from '@/components/vehicle/Card.vue'
import AddCard from '@/components/vehicle/AddCard.vue'

defineProps({
  vehicles: {
    type: Array,
    required: true
  }
})

const emit = defineEmits(['open-detail', 'open-add-dialog'])
</script>