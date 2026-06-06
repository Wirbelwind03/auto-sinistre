<template>
    <v-navigation-drawer
        permanent
    >
        <v-list-item
            prepend-icon="mdi-car-emergency"
            title="Auto Assureur"
            subtitle="Gestion sinistres"
            nav
            class="py-4"
        >
            <template v-slot:prepend>
            <v-avatar color="yellow-accent-3" rounded="lg" size="36">
                <v-icon color="black" size="20">mdi-car-emergency</v-icon>
            </v-avatar>
            </template>
        </v-list-item>

        <v-divider></v-divider>

        <v-list density="compact" nav>
            <v-list-subheader>PRINCIPAL</v-list-subheader>

            <v-list-item prepend-icon="mdi-view-dashboard" title="Dashboard" value="dashboard" rounded="lg"></v-list-item>
            <v-list-item prepend-icon="mdi-car-multiple" title="Véhicules" value="vehicules" rounded="lg"></v-list-item>
            <v-list-item prepend-icon="mdi-account-group" title="Assurés" value="assures" rounded="lg"></v-list-item>

            <v-list-subheader>SINISTRES</v-list-subheader>
            <v-list-item prepend-icon="mdi-alert-circle" title="Déclarations" value="declarations" rounded="lg"></v-list-item>
            <v-list-item prepend-icon="mdi-magnify" title="Suivi" value="suivi" rounded="lg"></v-list-item>
            <v-list-item prepend-icon="mdi-history" title="Historique" value="historique" rounded="lg"></v-list-item>

            <v-list-subheader>ANALYTICS</v-list-subheader>
            <v-list-item prepend-icon="mdi-chart-bar" title="Rapports" value="rapports" rounded="lg"></v-list-item>
        </v-list>

        <template v-slot:append>
            <div v-if="auth.isAuthenticated">
                <v-divider></v-divider>
                <v-list density="compact" nav class="pa-2">
                    <v-list-item
                        
                        prepend-avatar="https://randomuser.me/api/portraits/men/85.jpg"
                        title="Test"
                        :subtitle="auth.role"
                        rounded="lg"
                    >
                    </v-list-item>
                </v-list>
            </div>

        </template>

    </v-navigation-drawer>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth'

const auth = useAuthStore()
</script>