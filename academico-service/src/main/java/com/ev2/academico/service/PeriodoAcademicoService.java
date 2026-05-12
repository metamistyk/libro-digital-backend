package com.ev2.academico.service;

import java.util.List;

import com.ev2.academico.dto.PeriodoAcademicoRequestDTO;
import com.ev2.academico.dto.PeriodoAcademicoResponseDTO;

public interface PeriodoAcademicoService {

    PeriodoAcademicoResponseDTO guardar(PeriodoAcademicoRequestDTO periodoAcademicoRequestDTO);

    List<PeriodoAcademicoResponseDTO> listarTodos();

    List<PeriodoAcademicoResponseDTO> listarActivos();

    PeriodoAcademicoResponseDTO buscarPorId(Long id);

    PeriodoAcademicoResponseDTO actualizar(Long id, PeriodoAcademicoRequestDTO periodoAcademicoRequestDTO);

    void eliminar(Long id);
}