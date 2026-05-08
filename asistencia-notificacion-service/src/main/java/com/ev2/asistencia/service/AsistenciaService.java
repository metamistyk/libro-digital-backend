package com.ev2.asistencia.service;

import com.ev2.asistencia.dto.AsistenciaRequestDTO;
import com.ev2.asistencia.dto.AsistenciaResponseDTO;

public interface AsistenciaService {

    AsistenciaResponseDTO guardar(AsistenciaRequestDTO asistenciaRequestDTO);
}