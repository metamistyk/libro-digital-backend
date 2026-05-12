package com.ev2.asistencia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ev2.asistencia.dto.NotificacionRequestDTO;
import com.ev2.asistencia.dto.NotificacionResponseDTO;
import com.ev2.asistencia.event.AsistenciaRegistradaEvent;
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
        notificacion.setEstado(EstadoNotificacion.PENDIENTE);

        Notificacion notificacionGuardada = notificacionRepository.save(notificacion);

        return convertirADTO(notificacionGuardada);
    }

    @Override
    public void crearDesdeAsistenciaRegistrada(AsistenciaRegistradaEvent event) {

        Notificacion notificacion = new Notificacion();

        notificacion.setDestinatarioId(event.getEstudianteId());
        notificacion.setMensaje("Se registró asistencia con estado: " + event.getEstado());
        notificacion.setFechaCreacion(LocalDateTime.now());
        notificacion.setEstado(EstadoNotificacion.PENDIENTE);

        notificacionRepository.save(notificacion);
    }

    @Override
    public List<NotificacionResponseDTO> listarTodas() {

        return notificacionRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
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