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
        <Toolbar @change-view="changeView"/>
        
        <!-- Grillage de véhicules -->
        <GridViewMode v-if="viewMode=='grid'"
            :vehicles="vehicleStore.vehicles"
            @open-detail="openDetail"
            @open-add-dialog="addDialog = true"
        >
        </GridViewMode>

        <!-- VUE LISTE -->
        <!-- 
        <ListViewMode v-else
            :vehicles="vehicleStore.vehicles"
            @open-detail="openDetail"
        >
        </ListViewMode>-->
        

    </v-container>

    <AddDialog v-model="addDialog"/>
    <DetailDialog v-model="detailDialog" :vehicle="selectedVehicle"/>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth'
import { onMounted, ref } from 'vue'

import GridViewMode from '@/components/vehicle/GridViewMode.vue'
import AddDialog from '@/components/vehicle/AddDialog.vue'
import Toolbar from '@/components/vehicle/Toolbar.vue'
import { useVehicleStore } from '@/stores/vehicleStore'
import { useAppBarStore } from '@/stores/appBarStore.js'
import DetailDialog from '@/components/vehicle/DetailDialog.vue'
import ListViewMode from '../components/vehicle/ListViewMode.vue'

const auth = useAuthStore()
const vehicleStore = useVehicleStore()
const appBarStore = useAppBarStore()

// Ouvre le dialog pour ajouter un véhicule
const addDialog = ref(false)

onMounted(async() => {
    appBarStore.setBar('Mes véhicules', {
        label: 'Ajouter un véhicule',
        icon: 'mdi-plus',
        color: 'yellow-accent-3',
        onClick: () => { addDialog.value = true }
    })

    await vehicleStore.getUserVehicles()
    .then((response) => {
      console.log('Véhicules :', vehicleStore.vehicles)
    })
    .catch((e) => {
      console.error('Erreur lors du chargement des voitures :', e)
    })
})

const role = ref(auth.user?.role?.name || 'CLIENT')

// Change comment sont afficher les véhicules
const viewMode = ref('grid')
function changeView(viewModeChoosen){
    viewMode.value = viewModeChoosen
}



// Ouvre les détails du véhicule
const detailDialog = ref(false)
const selectedVehicle = ref(false)
function openDetail(vehicle){
  selectedVehicle.value = vehicle
  detailDialog.value = true
}
</script>