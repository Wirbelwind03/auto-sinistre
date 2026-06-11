<style scoped>
  :root {
    --v-font-family: 'Nunito', sans-serif !important;
  }
  * { font-family: 'Nunito', sans-serif !important; }

  .step-active { background: #1565c0 !important; color: white !important; }
  .step-done { background: #43a047 !important; color: white !important; }
  .step-idle { background: #e0e0e0 !important; color: #9e9e9e !important; }
</style>

<template>
    <v-sheet color="blue-darken-2" class="px-6 py-3">
        <div class="d-flex align-center ga-2">
            <template v-for="(step, i) in steps" :key="i">
            <div class="d-flex align-center ga-2">
                <v-avatar
                size="28"
                :class="i < currentStep ? 'step-done' : i === currentStep ? 'step-active' : 'step-idle'"
                >
                <v-icon v-if="i < currentStep" size="16">mdi-check</v-icon>
                <span v-else style="font-size:.8rem;font-weight:700">{{ i + 1 }}</span>
                </v-avatar>
                <span :class="i === currentStep ? 'text-white font-weight-bold' : 'text-blue-lighten-3'" style="font-size:.82rem">
                {{ step }}
                </span>
            </div>
            <v-icon v-if="i < steps.length - 1" color="blue-lighten-3" size="16">mdi-chevron-right</v-icon>
            </template>
        </div>
    </v-sheet>
</template>

<script setup>
import { ref } from 'vue'

const props = defineProps({
  steps: {
    type: Array,
    required: true
  },
  currentStep: { type: Number, required: true }
})
</script>