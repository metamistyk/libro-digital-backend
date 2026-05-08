package com.ev2.asistencia.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ev2.asistencia.dto.NotificacionRequestDTO;
import com.ev2.asistencia.dto.NotificacionResponseDTO;
import com.ev2.asistencia.model.EstadoNotificacion;
import com.ev2.asistencia.model.Notificacion;
import com.ev2.asistencia.repository.NotificacionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificacionServiceImpl implements NotificacionService {

    private final NotificacionRepository notificacionRepository;

    @Override
    public NotificacionResponseDTO guardar(NotificacionRequestDTO notificacionRequestDTO) {

        Notificacion notificacion = new Notificacion();

        notificacion.setDestinatarioId(notificacionRequestDTO.getDestinatarioId());
        notificacion.setMensaje(notificacionRequestDTO.getMensaje());
        notificacion.setFechaCreacion(LocalDateTime.now());

        // Por ahora toda notificación nueva inicia como PENDIENTE
        notificacion.setEstado(EstadoNotificacion.PENDIENTE);

        Notificacion notificacionGuardada = notificacionRepository.save(notificacion);

        return convertirADTO(notificacionGuardada);
    }

    private NotificacionResponseDTO convertirADTO(Notificacion notificacion) {

        NotificacionResponseDTO dto = new NotificacionResponseDTO();

        dto.setId(notificacion.getId());
        dto.setDestinatarioId(notificacion.getDestinatarioId());
        dto.setMensaje(notificacion.getMensaje());
        dto.setFechaCreacion(notificacion.getFechaCreacion());
        dto.setEstado(notificacion.getEstado());

        return dto;
    }
}