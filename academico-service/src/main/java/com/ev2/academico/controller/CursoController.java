package com.ev2.academico.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ev2.academico.dto.CursoRequestDTO;
import com.ev2.academico.dto.CursoResponseDTO;
import com.ev2.academico.service.CursoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public CursoResponseDTO guardar(@RequestBody CursoRequestDTO cursoRequestDTO) {
        return cursoService.guardar(cursoRequestDTO);
    }

    @GetMapping
    public List<CursoResponseDTO> listarTodos() {
        return cursoService.listarTodos();
    }

    @GetMapping("/{id}")
    public CursoResponseDTO buscarPorId(@PathVariable Long id) {
        return cursoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public CursoResponseDTO actualizar(
            @PathVariable Long id,
            @RequestBody CursoRequestDTO cursoRequestDTO) {

        return cursoService.actualizar(id, cursoRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
    }
}