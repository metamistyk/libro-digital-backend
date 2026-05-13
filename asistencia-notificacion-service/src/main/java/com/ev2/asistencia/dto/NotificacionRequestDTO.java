package com.ev2.asistencia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificacionRequestDTO {

    private Long destinatarioId;

    private String mensaje;
}