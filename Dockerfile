# Usa Java 17
FROM eclipse-temurin:17-jdk

# Crea carpeta de trabajo
WORKDIR /app

# Copia todo el proyecto al contenedor
COPY . .

# Compila el proyecto (sin correr tests)
RUN ./mvnw clean package -DskipTests

# Expone el puerto que Spring usará
EXPOSE 8080

# Comando para iniciar la app
CMD ["java", "-jar", "target/ApiEdu-0.0.1-SNAPSHOT.jar"]
