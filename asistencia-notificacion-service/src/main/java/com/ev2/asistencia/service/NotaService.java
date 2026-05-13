package com.ev2.asistencia.service;

import java.util.List;

import com.ev2.asistencia.dto.NotaRequestDTO;
import com.ev2.asistencia.dto.NotaResponseDTO;

public interface NotaService {

    NotaResponseDTO guardar(
            NotaRequestDTO notaRequestDTO);

    List<NotaResponseDTO> listarPorEstudiante(
            Long estudianteId);
}