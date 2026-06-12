## Description

Application web full-stack de gestion et d'analyse des sinistres automobiles.

## Fonctionnalités
- **Gestion des véhicules assurés** — enregistrement des véhicules et association à leurs propriétaires
- **API REST complète** — consommée par le frontend Vue

## Tech Stack
| Couche | Technologies |
|---|---|
| Frontend | Vue 3, Vuetify 3, Pinia, Axios, Vite |
| Backend | Spring Boot, Spring Security, JPA/Hibernate, Flyway |
| Base de données | PostgreSQL |
| Conteneurisation | Docker, Docker Compose |

## APIs Utilisées

- [API Adresse](https://www.data.gouv.fr/dataservices/api-adresse-base-adresse-nationale-ban)
  
Pour obtenir les lieux plus précisement. Par exemple lors de la déclaration du sinistre.

## Installation 

### Prérequis

- [Docker](https://www.docker.com/) et Docker Compose

#### 1. Cloner le dépôt
```bash
git clone https://github.com/Wirbelwind03/auto-sinistre.git
cd auto-sinistre
```

### 2. Configurer les variables d'environnement
 
Copier le fichier d'exemple et renseigner les valeurs :
 
```bash
cp .env.example .env
```
 
```env
# Base de données
DB_USER=your_user
DB_PASSWORD=your_password
 
# JWT
JWT_SECRET=your_secret_key
```
 
### 3. Lancer l'application
 
```bash
docker compose up --build
```

