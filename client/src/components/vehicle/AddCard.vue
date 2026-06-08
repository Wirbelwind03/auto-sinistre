<style scoped>
.add-card {
  border: 2px dashed rgb(var(--v-theme-primary)) !important;
  background: rgba(var(--v-theme-primary), 0.05) !important;
  transition: all .2s !important;
  cursor: pointer;
  min-height: 280px;
}
.add-card:hover {
  border-color: rgb(var(--v-theme-primary)) !important;
  background: rgba(var(--v-theme-primary), 0.25) !important;
  transform: translateY(-4px);
}
</style>

<template>
    <v-col cols="12" sm="6" md="4">
        <v-card class="add-card d-flex align-center justify-center" rounded="xl" elevation="0">
            <v-card-text class="text-center pa-8">
            <v-avatar color="blue-lighten-4" size="56" class="mb-3">
                <v-icon color="blue-darken-2" size="28">mdi-plus</v-icon>
            </v-avatar>
            <div class="text-body-1 font-weight-bold text-blue-darken-2 mb-1">Ajouter un véhicule</div>
            <div class="text-caption text-medium-emphasis">Enregistrez un nouveau véhicule assuré</div>
            </v-card-text>
        </v-card>
    </v-col>
</template>