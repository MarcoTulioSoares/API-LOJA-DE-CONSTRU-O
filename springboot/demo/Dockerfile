# syntax=docker/dockerfile:1

# ---------- Stage 1: Build ----------
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copia o pom e baixa deps (cache melhor)
COPY pom.xml ./
RUN mvn -q -B -DskipTests dependency:go-offline

# Copia o código e compila
COPY src ./src
RUN mvn -q -B -DskipTests clean package

# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Expõe a porta padrão local (8089). Render/Heroku vão injetar PORT e ignoram isso.
EXPOSE 8089

# (Opcional) timezone consistente
ENV TZ=UTC

# (Opcional) tunar a JVM em containers
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75 -XX:InitialRAMPercentage=50 -Dfile.encoding=UTF-8"

# Copia o jar do stage de build
COPY --from=builder /app/target/*.jar app.jar

# Cria usuário não-root (melhor prática)
RUN useradd -r -u 1001 appuser && chown appuser:appuser /app
USER appuser

# Usa JAVA_OPTS e respeita PORT (Heroku/Render), senão cai em 8089
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=${PORT:-8089} -jar app.jar"]
