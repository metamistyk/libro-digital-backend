package com.ev2.asistencia.service;

import java.util.List;

import com.ev2.asistencia.dto.AsistenciaRequestDTO;
import com.ev2.asistencia.dto.AsistenciaResponseDTO;

public interface AsistenciaService {

    AsistenciaResponseDTO guardar(AsistenciaRequestDTO asistenciaRequestDTO);

    List<AsistenciaResponseDTO> listarPorEstudiante(Long estudianteId);
}