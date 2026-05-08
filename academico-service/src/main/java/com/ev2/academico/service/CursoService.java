package com.ev2.academico.service;

import com.ev2.academico.dto.CursoRequestDTO;
import com.ev2.academico.dto.CursoResponseDTO;

public interface CursoService {

    CursoResponseDTO guardar(CursoRequestDTO cursoRequestDTO);
}