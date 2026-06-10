<style scoped>
</style>

<template>
    <v-card v-if="role === 'Admin'" rounded="xl" elevation="1" class="mb-4">
        <v-card-text class="pa-4">
        <v-row align="center">
            <v-col cols="4">
            <v-text-field placeholder="Rechercher plaque, marque, propriétaire…" variant="outlined" density="compact" prepend-inner-icon="mdi-magnify" hide-details rounded="lg" color="blue-darken-2" clearable></v-text-field>
            </v-col>
            <v-col cols="2">
            <v-select label="Statut" :items="['Tous','Actif','Suspendu','Résilié']" variant="outlined" density="compact" hide-details rounded="lg" color="blue-darken-2"></v-select>
            </v-col>
            <v-col cols="2">
            <v-select label="Formule" :items="['Toutes','Tous risques','Tiers étendu','Tiers simple']" variant="outlined" density="compact" hide-details rounded="lg" color="blue-darken-2"></v-select>
            </v-col>
            <v-spacer></v-spacer>
            <v-col cols="auto">
            <v-btn-toggle mandatory density="compact" color="blue-darken-2" rounded="lg" divided>
                <v-btn value="table" icon="mdi-table" size="small"></v-btn>
                <v-btn value="grid" icon="mdi-view-grid" size="small"></v-btn>
            </v-btn-toggle>
            </v-col>
        </v-row>
        </v-card-text>
    </v-card>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth'
import { ref } from 'vue'

const auth = useAuthStore()

const role = ref(auth.user?.role?.name || 'CLIENT')
</script>