# Stage 1: Compile and Build angular project/codebase.

# 1.1 Use official node image as the official image:
FROM node:latest as build
# 1.2 Set the working directory (On définit le répertoire /usr/local/app comme répertoire de travail. Toutes les commandes
#       suivantes s'exécuteront à partir de ce répertoire).
WORKDIR /usr/local/app
# 1.3 COPY project source code to app working dir
COPY ./ /usr/local/app/
# 1.4 Install all dependencies
RUN npm install
#1.5 Generate the build of the application
RUN npm run build

# Stage 2: Deploy app on NGINX web server to servce app (On démarre une nouvelle étape de build en utilisant l'image
#            officielle de NGINX. Celle-ci servira à héberger les fichiers statiques générés).
# 2.1 Use NGINX official image as the base image:
FROM nginx:latest
# 2.2 Copy the build output to replace the nginx default contents:
COPY --from=build /usr/local/app/dist/angular-front-end/browser /usr/share/nginx/html
# Expose port 80 (On indique que le container écoutera sur le port 80, port standard utilisé par NGINX pour servir les pages web).
EXPOSE 80


