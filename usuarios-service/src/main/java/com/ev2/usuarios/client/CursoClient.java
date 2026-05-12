package com.ev2.usuarios.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CursoClient {

    private final RestTemplate restTemplate;

    @CircuitBreaker(name = "academicoService", fallbackMethod = "fallbackCurso")
    public Boolean validarCurso(Long cursoId) {

        String url = "http://localhost:8081/api/v1/cursos/" + cursoId;

        restTemplate.getForObject(url, Object.class);

        return true;
    }

    public Boolean fallbackCurso(Long cursoId, Exception exception) {
        return false;
    }
}