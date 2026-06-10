<template>
  <v-app-bar color="blue-darken-3" elevation="0" border="b">
    <v-app-bar-title>
      <span class="font-weight-bold">{{ appBarStore.title }}</span>
    </v-app-bar-title>
    <template v-slot:append>
      <v-btn
        v-if="appBarStore.action"
        :color="appBarStore.action.color ?? 'yellow-accent-3'"
        :prepend-icon="appBarStore.action.icon"
        variant="flat"
        class="mr-3 text-black font-weight-bold"
        @click="appBarStore.action.onClick"
      >
        {{ appBarStore.action.label }}
      </v-btn>
    </template>
  </v-app-bar>
</template>

<script setup>
import { useAppBarStore } from '@/stores/appBarStore'
const appBarStore = useAppBarStore()
</script>