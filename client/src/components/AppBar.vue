<template>
  <v-app-bar color="blue-darken-3" elevation="0" border="b">
    <v-app-bar-title>
      <span class="font-weight-bold">Mes véhicules</span>
    </v-app-bar-title>
    <template v-slot:append>
      <v-btn color="yellow-accent-3" variant="flat" class="mr-3 text-black font-weight-bold" prepend-icon="mdi-plus" @click="addDialog = true">
        Ajouter un véhicule
      </v-btn>
    </template>
  </v-app-bar>
</template>