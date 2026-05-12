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

import com.ev2.academico.dto.PeriodoAcademicoRequestDTO;
import com.ev2.academico.dto.PeriodoAcademicoResponseDTO;
import com.ev2.academico.service.PeriodoAcademicoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/periodos-academicos")
@RequiredArgsConstructor
public class PeriodoAcademicoController {

    private final PeriodoAcademicoService periodoAcademicoService;

    @PostMapping
    public PeriodoAcademicoResponseDTO guardar(@RequestBody PeriodoAcademicoRequestDTO periodoAcademicoRequestDTO) {
        return periodoAcademicoService.guardar(periodoAcademicoRequestDTO);
    }

    @GetMapping
    public List<PeriodoAcademicoResponseDTO> listarTodos() {
        return periodoAcademicoService.listarTodos();
    }

    @GetMapping("/activos")
    public List<PeriodoAcademicoResponseDTO> listarActivos() {
        return periodoAcademicoService.listarActivos();
    }

    @GetMapping("/{id}")
    public PeriodoAcademicoResponseDTO buscarPorId(@PathVariable Long id) {
        return periodoAcademicoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public PeriodoAcademicoResponseDTO actualizar(
            @PathVariable Long id,
            @RequestBody PeriodoAcademicoRequestDTO periodoAcademicoRequestDTO) {

        return periodoAcademicoService.actualizar(id, periodoAcademicoRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        periodoAcademicoService.eliminar(id);
    }
}