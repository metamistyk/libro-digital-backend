package com.ev2.asistencia.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ev2.asistencia.dto.NotaRequestDTO;
import com.ev2.asistencia.dto.NotaResponseDTO;
import com.ev2.asistencia.service.NotaService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService notaService;

    @PostMapping
    public NotaResponseDTO guardar(
            @Valid @RequestBody
            NotaRequestDTO notaRequestDTO) {

        return notaService.guardar(
                notaRequestDTO);
    }

    @GetMapping
    public List<NotaResponseDTO>
            listarPorEstudiante(
                    @RequestParam Long estudianteId) {

        return notaService
                .listarPorEstudiante(estudianteId);
    }
}