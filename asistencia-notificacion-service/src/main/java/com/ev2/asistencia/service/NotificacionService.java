package com.ev2.asistencia.service;

import com.ev2.asistencia.dto.NotificacionRequestDTO;
import com.ev2.asistencia.dto.NotificacionResponseDTO;

public interface NotificacionService {

    NotificacionResponseDTO guardar(NotificacionRequestDTO notificacionRequestDTO);
}