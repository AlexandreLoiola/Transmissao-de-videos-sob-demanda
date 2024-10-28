# Etapa 1: Construir o aplicativo
# Usando uma imagem oficial do Node.js para compilar o projeto
FROM node:18-alpine AS builder

# Definir diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar os arquivos package.json e package-lock.json (se existir)
COPY package*.json ./

# Instalar dependências do projeto
RUN npm install --frozen-lockfile

# Copiar o restante do código fonte
COPY . .

# Rodar o script de build para gerar os arquivos estáticos
RUN npm run build

# Etapa 2: Servir o aplicativo
# Usar uma imagem Nginx para servir os arquivos estáticos
FROM nginx:alpine

# Remover o arquivo de configuração default do Nginx e ajustar permissões
RUN rm /etc/nginx/conf.d/default.conf

# Copiar arquivos de configuração customizados (se tiver)
COPY nginx.conf /etc/nginx/conf.d

# Copiar os arquivos estáticos gerados para o diretório padrão do Nginx
COPY --from=builder /app/dist /usr/share/nginx/html

# Expor a porta para o Nginx
EXPOSE 80

# Comando para rodar o Nginx
CMD ["nginx", "-g", "daemon off;"]
