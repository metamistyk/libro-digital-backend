package com.ev2.asistencia.service;

import java.util.List;

import com.ev2.asistencia.dto.NotificacionRequestDTO;
import com.ev2.asistencia.dto.NotificacionResponseDTO;
import com.ev2.asistencia.event.AsistenciaRegistradaEvent;

public interface NotificacionService {

    NotificacionResponseDTO guardar(NotificacionRequestDTO notificacionRequestDTO);

    void crearDesdeAsistenciaRegistrada(AsistenciaRegistradaEvent event);

    List<NotificacionResponseDTO> listarTodas();
}