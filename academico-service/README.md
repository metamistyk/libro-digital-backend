# Academico Service — Libro Digital

Microservicio encargado de gestionar los datos académicos del sistema:
cursos, asignaturas, periodos lectivos y asignaciones de docentes.

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
- Base de datos `academico_db` disponible en el puerto `5433`

## Instalación y ejecución

```bash
# 1. Levantar la base de datos (desde la raíz del proyecto backend)
docker-compose up -d academico-db

# 2. Ejecutar el servicio
cd academico-service
./mvnw spring-boot:run
```

El servicio quedará disponible en `http://localhost:8081`

## Endpoints principales

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/v1/cursos` | Lista todos los cursos |
| POST | `/api/v1/cursos` | Crea un curso |
| GET | `/api/v1/asignaturas` | Lista todas las asignaturas |
| POST | `/api/v1/asignaturas` | Crea una asignatura |
| GET | `/api/v1/periodos` | Lista todos los periodos |
| GET | `/api/v1/asignaciones-docentes` | Lista asignaciones de docentes |

## Documentación Swagger