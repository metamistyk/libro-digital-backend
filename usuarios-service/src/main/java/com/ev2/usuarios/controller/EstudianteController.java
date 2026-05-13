package com.ev2.usuarios.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ev2.usuarios.dto.EstudianteRequestDTO;
import com.ev2.usuarios.dto.EstudianteResponseDTO;
import com.ev2.usuarios.service.EstudianteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @PostMapping
    public EstudianteResponseDTO guardar(
            @RequestBody EstudianteRequestDTO estudianteRequestDTO) {

        return estudianteService.guardar(estudianteRequestDTO);
    }

    @GetMapping
    public List<EstudianteResponseDTO> listarTodos() {

        return estudianteService.listarTodos();
    }
}