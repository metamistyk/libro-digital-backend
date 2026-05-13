package com.ev2.asistencia.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ev2.asistencia.dto.AnotacionRequestDTO;
import com.ev2.asistencia.dto.AnotacionResponseDTO;
import com.ev2.asistencia.service.AnotacionService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/anotaciones")
@RequiredArgsConstructor
public class AnotacionController {

    private final AnotacionService anotacionService;

    @PostMapping
    public AnotacionResponseDTO guardar(
            @Valid @RequestBody
            AnotacionRequestDTO anotacionRequestDTO) {

        return anotacionService.guardar(
                anotacionRequestDTO);
    }

    @GetMapping
    public List<AnotacionResponseDTO>
            listarPorEstudiante(
                    @RequestParam Long estudianteId) {

        return anotacionService
                .listarPorEstudiante(estudianteId);
    }
}