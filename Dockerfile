# =================================
# Etapa 1 - build con Gradle
# =================================
FROM gradle:8.7-jdk21 AS builder

WORKDIR /app

# Copiar archivos de configuración primero (para cache)
COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle

# Dar permisos al wrapper (importante en Linux container)
RUN chmod +x gradlew

# Descargar dependencias
RUN ./gradlew dependencies --no-daemon

# Copiar código fuente
COPY src src

# Construir jar
RUN ./gradlew bootJar --no-daemon


# =================================
# Etapa 2 - imagen final con Java 21
# =================================
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Crear usuario seguro
RUN useradd -ms /bin/bash springuser

# Copiar jar desde builder
COPY --from=builder /app/build/libs/*.jar app.jar

# Puerto Spring Boot
EXPOSE 8080

# Variables opcionales
ENV JAVA_OPTS=""
ENV SPRING_PROFILES_ACTIVE=default

RUN chown -R springuser:springuser /app

USER springuser

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -jar app.jar"]
