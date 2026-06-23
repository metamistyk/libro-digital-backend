# Asistencia Notificacion Service — Libro Digital

Microservicio encargado de gestionar asistencias, notas, anotaciones,
mensajes entre usuarios y notificaciones automáticas via Apache Kafka.

## Tecnologías

- Java 21
- Spring Boot 3.3
- Spring Data JPA + Hibernate
- HikariCP (pool de conexiones)
- PostgreSQL
- Apache Kafka (productor y consumidor)
- Spring Security + OAuth2 (Auth0)
- Springdoc OpenAPI (Swagger)

## Requisitos previos

- Java 21 instalado
- Maven instalado (o usar el wrapper `./mvnw`)
- Docker corriendo con el `docker-compose.yml` del proyecto levantado
- Base de datos `asistencia_db` disponible en el puerto `5435`
- Kafka disponible en el puerto `9092`

## Instalación y ejecución

```bash
# 1. Levantar infraestructura (desde la raíz del proyecto backend)
docker-compose up -d asistencia-db kafka zookeeper

# 2. Ejecutar el servicio
cd asistencia-notificacion-service
./mvnw spring-boot:run
```

El servicio quedará disponible en `http://localhost:8083`

## Flujo Kafka

Cuando se registra una asistencia, el servicio **produce** un evento al topic
`asistencia-registrada`. El consumidor del mismo servicio lee ese evento y
crea automáticamente una **notificación** asociada al estudiante.

Para monitorear los topics de Kafka, accede a Kafdrop en: