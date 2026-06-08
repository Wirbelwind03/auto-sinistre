<style scoped>
* { font-family: 'Nunito', sans-serif !important; }
.vehicle-row { cursor: pointer; transition: background .15s; }
.vehicle-row:hover { background: rgba(21,101,192,0.04) !important; }
.plate-badge { font-family: 'Nunito', monospace !important; font-weight: 800 !important; letter-spacing: .08em; font-size: .82rem !important; }
.detail-panel { position: sticky; top: 80px; max-height: calc(100vh - 100px); overflow-y: auto; }
@keyframes slideIn { from{opacity:0;transform:translateX(20px)} to{opacity:1;transform:translateX(0)} }
.detail-panel { animation: slideIn .25s ease; }
.add-dialog-card { border-top: 4px solid #1565c0 !important; }
</style>

<template>
    <v-container fluid class="pa-6">
        
        <!-- Barre d'outils -->
        <Toolbar>
            <v-card rounded="xl" elevation="1" class="mb-4">
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
        </Toolbar>
        
        <!-- Grillage de véhicules -->
        <v-row>
            <!-- Carte ajouter un véhicule -->
            <AddCard>
                <v-card class="add-card d-flex align-center justify-center" rounded="xl" elevation="0" @click="addDialog = true">
                    <v-card-text class="text-center pa-8">
                    <v-avatar color="blue-lighten-4" size="56" class="mb-3">
                        <v-icon color="blue-darken-2" size="28">mdi-plus</v-icon>
                    </v-avatar>
                    <div class="text-body-1 font-weight-bold text-blue-darken-2 mb-1">Ajouter un véhicule</div>
                    <div class="text-caption text-medium-emphasis">Enregistrez un nouveau véhicule assuré</div>
                    </v-card-text>
                </v-card>
            </AddCard>
        </v-row>
    </v-container>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth'
import { ref } from 'vue'

import AddCard from '@/components/vehicle/AddCard.vue'
import AddDialog from '@/components/vehicle/AddDialog.vue'
import Toolbar from '@/components/vehicle/Toolbar.vue'


const auth = useAuthStore()

const role = ref(auth.user?.role?.name || 'CLIENT')

const addDialog = ref(false)
</script>