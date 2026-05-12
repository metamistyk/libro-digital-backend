package com.ev2.academico.service;

import java.util.List;

import com.ev2.academico.dto.CursoRequestDTO;
import com.ev2.academico.dto.CursoResponseDTO;

public interface CursoService {

    CursoResponseDTO guardar(CursoRequestDTO cursoRequestDTO);

    List<CursoResponseDTO> listarTodos();

    CursoResponseDTO buscarPorId(Long id);

    CursoResponseDTO actualizar(Long id, CursoRequestDTO cursoRequestDTO);

    void eliminar(Long id);
}