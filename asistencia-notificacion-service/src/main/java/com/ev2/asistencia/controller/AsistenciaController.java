package com.ev2.asistencia.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ev2.asistencia.dto.AsistenciaRequestDTO;
import com.ev2.asistencia.dto.AsistenciaResponseDTO;
import com.ev2.asistencia.service.AsistenciaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    @PostMapping
    public AsistenciaResponseDTO guardar(@RequestBody AsistenciaRequestDTO asistenciaRequestDTO) {
        return asistenciaService.guardar(asistenciaRequestDTO);
    }
}