package com.ev2.asistencia.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MensajeResponseDTO {

    private Long id;
    private Long remitenteId;
    private Long destinatarioId;
    private String contenido;
    private LocalDateTime fechaEnvio;
    private Boolean leido;
}