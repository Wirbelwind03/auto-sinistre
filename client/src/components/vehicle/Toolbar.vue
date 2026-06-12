<style scoped>
</style>

<template>
    <v-card rounded="xl" elevation="1" class="mb-4">
        <v-card-text class="pa-4">
        <v-row align="center">
            <v-col cols="4">
            <v-text-field placeholder="Rechercher plaque, marque, modèle..." variant="outlined" density="compact" prepend-inner-icon="mdi-magnify" hide-details rounded="lg" color="blue-darken-2" clearable></v-text-field>
            </v-col>
            <v-col cols="2">
            <v-select label="Statut" :items="['Tous','Actif','Suspendu','Résilié']" variant="outlined" density="compact" hide-details rounded="lg" color="blue-darken-2"></v-select>
            </v-col>
            
            <v-spacer></v-spacer>
            
            <!-- 
            <v-col cols="auto">
                <v-btn-toggle mandatory density="compact" color="blue-darken-2" rounded="lg" divided>
                    <v-btn value="table" icon="mdi-table" size="small" @click="emit('change-view', 'table')"></v-btn>
                    <v-btn value="grid" icon="mdi-view-grid" size="small" @click="emit('change-view', 'grid')"></v-btn>
                </v-btn-toggle>
            </v-col>
            -->

        </v-row>
        </v-card-text>
    </v-card>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth'
import { ref } from 'vue'

const auth = useAuthStore()

const role = ref(auth.user?.role?.name || 'CLIENT')

const emit = defineEmits(['change-view'])
</script>