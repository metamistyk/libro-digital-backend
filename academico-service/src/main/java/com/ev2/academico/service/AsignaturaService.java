package com.ev2.academico.service;

import java.util.List;

import com.ev2.academico.dto.AsignaturaRequestDTO;
import com.ev2.academico.dto.AsignaturaResponseDTO;

public interface AsignaturaService {

    AsignaturaResponseDTO guardar(AsignaturaRequestDTO asignaturaRequestDTO);

    List<AsignaturaResponseDTO> listarTodas();

    List<AsignaturaResponseDTO> listarPorCurso(Long cursoId);

    AsignaturaResponseDTO buscarPorId(Long id);

    AsignaturaResponseDTO actualizar(Long id, AsignaturaRequestDTO asignaturaRequestDTO);

    void eliminar(Long id);
}