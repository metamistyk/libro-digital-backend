package com.ev2.asistencia.event;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AsistenciaRegistradaEvent {

    private Long asistenciaId;

    private Long estudianteId;

    private LocalDateTime fechaHora;

    private String estado;
}