# Build avec node
FROM node:20-alpine AS builder
WORKDIR /app
# Copier package.json et package-lock.json 
COPY package*.json ./
# Installer les dépendances
RUN npm install
# Copier les fichiers du projet dans le répertoire du travail
COPY . .
# Construire l'application
RUN npm run build 

# Nginx
FROM nginx:alpine
COPY --from=builder /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80