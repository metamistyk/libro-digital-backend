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

import com.ev2.academico.dto.AsignaturaRequestDTO;
import com.ev2.academico.dto.AsignaturaResponseDTO;
import com.ev2.academico.service.AsignaturaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/asignaturas")
@RequiredArgsConstructor
public class AsignaturaController {

    private final AsignaturaService asignaturaService;

    @PostMapping
    public AsignaturaResponseDTO guardar(@RequestBody AsignaturaRequestDTO asignaturaRequestDTO) {
        return asignaturaService.guardar(asignaturaRequestDTO);
    }

    @GetMapping
    public List<AsignaturaResponseDTO> listarTodas() {
        return asignaturaService.listarTodas();
    }

    @GetMapping("/{id}")
    public AsignaturaResponseDTO buscarPorId(@PathVariable Long id) {
        return asignaturaService.buscarPorId(id);
    }

    @GetMapping("/por-curso")
    public List<AsignaturaResponseDTO> listarPorCurso(@RequestParam Long cursoId) {
        return asignaturaService.listarPorCurso(cursoId);
    }

    @PutMapping("/{id}")
    public AsignaturaResponseDTO actualizar(
            @PathVariable Long id,
            @RequestBody AsignaturaRequestDTO asignaturaRequestDTO) {

        return asignaturaService.actualizar(id, asignaturaRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        asignaturaService.eliminar(id);
    }
}