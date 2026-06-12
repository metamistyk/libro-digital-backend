package com.ev2.asistencia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MensajeRequestDTO {

    @NotNull
    private Long remitenteId;

    @NotNull
    private Long destinatarioId;

    @NotBlank
    private String contenido;
}