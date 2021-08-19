# Multi-stage
# 1) Node image for building frontend assets
# 2) nginx stage to serve frontend assets

# Name the node stage "builder"
FROM node:14 AS builder
# Set working directory
WORKDIR /app
# Copy all files from current directory to working dir in image
COPY ./client .
# install node modules and build assets
RUN npm i && npm run build

# nginx state for serving content
FROM nginx:alpine
# Set working directory to nginx asset directory
# Remove default nginx static assets
RUN rm -rf /usr/share/nginx/html/*
# Copy default nginx config
COPY client/nginx.conf etc/nginx/nginx.conf
# Copy static assets from builder stage
COPY --from=builder /app/dist/client /usr/share/nginx/html
# Containers run nginx with global directives and daemon off
ENTRYPOINT ["nginx", "-g", "daemon off;"]
