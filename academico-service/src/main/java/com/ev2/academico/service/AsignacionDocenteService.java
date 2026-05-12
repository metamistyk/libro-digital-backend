package com.ev2.academico.service;

import java.util.List;

import com.ev2.academico.dto.AsignacionDocenteRequestDTO;
import com.ev2.academico.dto.AsignacionDocenteResponseDTO;

public interface AsignacionDocenteService {

    AsignacionDocenteResponseDTO guardar(AsignacionDocenteRequestDTO asignacionDocenteRequestDTO);

    List<AsignacionDocenteResponseDTO> listarTodas();

    List<AsignacionDocenteResponseDTO> listarPorDocente(Long docenteId);

    List<AsignacionDocenteResponseDTO> listarPorCurso(Long cursoId);

    AsignacionDocenteResponseDTO buscarPorId(Long id);

    AsignacionDocenteResponseDTO actualizar(Long id, AsignacionDocenteRequestDTO asignacionDocenteRequestDTO);

    void eliminar(Long id);
}