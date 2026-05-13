package com.ev2.asistencia.dto;

import java.time.LocalDateTime;

import com.ev2.asistencia.model.EstadoNotificacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificacionResponseDTO {

    private Long id;

    private Long destinatarioId;

    private String mensaje;

    private LocalDateTime fechaCreacion;

    private EstadoNotificacion estado;
}