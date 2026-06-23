# Usuarios Service — Libro Digital

Microservicio encargado de gestionar usuarios y estudiantes del sistema,
incluyendo la asignación de roles (admin, docente, estudiante).

## Tecnologías

- Java 21
- Spring Boot 3.3
- Spring Data JPA + Hibernate
- HikariCP (pool de conexiones)
- PostgreSQL
- Spring Security + OAuth2 (Auth0)
- Springdoc OpenAPI (Swagger)

## Requisitos previos

- Java 21 instalado
- Maven instalado (o usar el wrapper `./mvnw`)
- Docker corriendo con el `docker-compose.yml` del proyecto levantado
- Base de datos `usuarios_db` disponible en el puerto `5434`

## Instalación y ejecución

```bash
# 1. Levantar la base de datos (desde la raíz del proyecto backend)
docker-compose up -d usuarios-db

# 2. Ejecutar el servicio
cd usuarios-service
./mvnw spring-boot:run
```

El servicio quedará disponible en `http://localhost:8082`

## Endpoints principales

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/v1/usuarios` | Lista todos los usuarios |
| POST | `/api/v1/usuarios` | Crea un usuario |
| GET | `/api/v1/estudiantes` | Lista todos los estudiantes |
| GET | `/api/v1/estudiantes/{id}` | Busca un estudiante por ID |
| POST | `/api/v1/estudiantes` | Crea un estudiante |

## Documentación Swagger