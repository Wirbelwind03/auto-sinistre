import { ref } from 'vue'

export function useAddressSearch(){
    const suggestions = ref([])
    const loading = ref(false)
    let debounceTimer = null

    function onSearch(val) {
        clearTimeout(debounceTimer)
        if (!val || val.length < 3) {
        suggestions.value = []
        return
        }
        debounceTimer = setTimeout(() => fetchAddresses(val), 300)
    }

    async function fetchAddresses(query) {
        loading.value = true
        try {
            const res = await fetch(
                `https://api-adresse.data.gouv.fr/search/?q=${encodeURIComponent(query)}&limit=6`
            )
            const data = await res.json()
            suggestions.value = data.features.map((f) => {
                const p = f.properties
                const street = p.name
                const city = p.city
                const postcode = p.postcode

                return {
                    label: `${street}, ${city} ${postcode}`,
                    value: `${street}, ${city} ${postcode}`,
                    postcode,
                    city,
                    lat: f.geometry.coordinates[1],
                    lon: f.geometry.coordinates[0],
                }
            })
        } catch (e) {
            console.error('Erreur API adresse :', e)
        } finally {
            loading.value = false
        }
    }

    return { suggestions, loading, onSearch }
}