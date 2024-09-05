# Usar a imagem do OpenJDK 21
FROM openjdk:21-jdk-slim

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Copiar o JAR da sua aplicação para dentro do contêiner
COPY target/picpaysimplificado-0.0.1-SNAPSHOT.jar /app/app.jar

# Expor a porta em que o Spring Boot roda (8080)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
