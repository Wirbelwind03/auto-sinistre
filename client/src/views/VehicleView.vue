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
        <Toolbar/>
        
        <!-- Grillage de véhicules -->
        <v-row>
            <!-- Carte ajouter un véhicule -->
            <AddCard @open-add-dialog="addDialog = true"/>
        </v-row>
        
        <AddDialog v-model="addDialog"/>

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