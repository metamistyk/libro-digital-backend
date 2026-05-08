package com.ev2.asistencia.dto;

import java.time.LocalDateTime;

import com.ev2.asistencia.model.EstadoAsistencia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AsistenciaResponseDTO {

    private Long id;

    private Long estudianteId;

    private LocalDateTime fechaHora;

    private EstadoAsistencia estado;
}