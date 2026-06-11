import { defineStore } from "pinia"
import { ref, computed, reactive } from 'vue'

export const useStepperBarStore = defineStore('stepperBar', () => {
    const currentStep = ref(1)

    return { currentStep }
})