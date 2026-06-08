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
    <div>
        <!-- Barre d'outils -->
        <v-container fluid class="pa-6">
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
        </v-container>
    </div>
</template>

<script setup>


</script>