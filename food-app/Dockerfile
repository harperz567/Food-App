# # Stage 1: Build the Angular app
# FROM node:22 AS build
# WORKDIR /app
# COPY package*.json ./
# RUN npm install
# COPY . .
# RUN npm run build 

# # Stage 2: Serve the Angular app using Nginx
# FROM nginx:alpine
# COPY --from=build /app/dist/food-app /usr/share/nginx/html
# EXPOSE 80
# CMD ["nginx", "-g", "daemon off;"]

# # Stage 1: Build the Angular app
# FROM node:18 AS build
# WORKDIR /app
# COPY package*.json ./
# RUN npm install
# COPY . .
# RUN npm run build

# # Stage 2: Serve the Angular app with SSR
# FROM node:18-alpine
# WORKDIR /app
# COPY --from=build /app/dist /app/dist
# COPY --from=build /app/package*.json /app/

# RUN npm ci --only=production


# EXPOSE 80
# # CMD ["node", "dist/food-app/server/main.server.mjs"]
# CMD ["node", "/app/dist/food-app/server/main.server.mjs"]


# Stage 1: Build the Angular app
FROM node:18 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Stage 2: Debug container
FROM node:18-alpine
WORKDIR /app
COPY --from=build /app/dist /app/dist
COPY --from=build /app/package*.json /app/
RUN npm ci --only=production

# 创建调试脚本
RUN echo '#!/bin/sh\n\
echo "=== DIRECTORY STRUCTURE ==="\n\
find /app -type f -name "*.mjs" | sort\n\
echo "=== NODE VERSION ==="\n\
node --version\n\
echo "=== PACKAGE.JSON ==="\n\
cat /app/package.json\n\
echo "=== ATTEMPTING TO START SERVER ==="\n\
node --enable-source-maps /app/dist/food-app/server/main.server.mjs || echo "Exit code: $?"\n\
echo "=== KEEPING CONTAINER ALIVE ==="\n\
tail -f /dev/null\n\
' > /app/debug.sh

RUN chmod +x /app/debug.sh

EXPOSE 80
# 启动调试脚本并保持容器运行
CMD ["/app/debug.sh"]