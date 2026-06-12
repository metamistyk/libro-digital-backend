package com.ev2.usuarios.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ev2.usuarios.dto.EstudianteRequestDTO;
import com.ev2.usuarios.dto.EstudianteResponseDTO;
import com.ev2.usuarios.service.EstudianteService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @PostMapping
    public EstudianteResponseDTO guardar(
            @Valid @RequestBody
            EstudianteRequestDTO estudianteRequestDTO) {

        return estudianteService.guardar(
                estudianteRequestDTO);
    }

    @GetMapping
    public List<EstudianteResponseDTO> listarTodos() {

        return estudianteService.listarTodos();
    }
    
    @GetMapping("/{id}")
    public EstudianteResponseDTO buscarPorId(@PathVariable Long id) {
        return estudianteService.buscarPorId(id);
    }
}