import { defineStore } from "pinia"
import { ref, computed, reactive } from 'vue'
import api from '@/services/api'

export const useAppBarStore = defineStore('appBar', () => {
    const title = ref('')
    const action = ref(null) 

    function setBar(newTitle, newAction = null) {
        title.value = newTitle
        action.value = newAction
    }

    return { title, action, setBar }
})