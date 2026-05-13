package com.ev2.asistencia.service;

import com.ev2.asistencia.dto.AnotacionRequestDTO;
import com.ev2.asistencia.dto.AnotacionResponseDTO;

public interface AnotacionService {

    AnotacionResponseDTO guardar(AnotacionRequestDTO anotacionRequestDTO);
}