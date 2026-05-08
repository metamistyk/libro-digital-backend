package com.ev2.asistencia.dto;

import com.ev2.asistencia.model.EstadoAsistencia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AsistenciaRequestDTO {

    private Long estudianteId;

    private EstadoAsistencia estado;
}