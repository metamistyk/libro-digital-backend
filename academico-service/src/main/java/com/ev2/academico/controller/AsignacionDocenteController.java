package com.ev2.academico.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ev2.academico.dto.AsignacionDocenteRequestDTO;
import com.ev2.academico.dto.AsignacionDocenteResponseDTO;
import com.ev2.academico.service.AsignacionDocenteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/asignaciones-docentes")
@RequiredArgsConstructor
public class AsignacionDocenteController {

    private final AsignacionDocenteService asignacionDocenteService;

    @PostMapping
    public AsignacionDocenteResponseDTO guardar(
            @RequestBody AsignacionDocenteRequestDTO asignacionDocenteRequestDTO) {

        return asignacionDocenteService.guardar(asignacionDocenteRequestDTO);
    }

    @GetMapping
    public List<AsignacionDocenteResponseDTO> listarTodas() {
        return asignacionDocenteService.listarTodas();
    }

    @GetMapping("/{id}")
    public AsignacionDocenteResponseDTO buscarPorId(@PathVariable Long id) {
        return asignacionDocenteService.buscarPorId(id);
    }

    @GetMapping("/por-docente")
    public List<AsignacionDocenteResponseDTO> listarPorDocente(@RequestParam Long docenteId) {
        return asignacionDocenteService.listarPorDocente(docenteId);
    }

    @GetMapping("/por-curso")
    public List<AsignacionDocenteResponseDTO> listarPorCurso(@RequestParam Long cursoId) {
        return asignacionDocenteService.listarPorCurso(cursoId);
    }

    @PutMapping("/{id}")
    public AsignacionDocenteResponseDTO actualizar(
            @PathVariable Long id,
            @RequestBody AsignacionDocenteRequestDTO asignacionDocenteRequestDTO) {

        return asignacionDocenteService.actualizar(id, asignacionDocenteRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        asignacionDocenteService.eliminar(id);
    }
}