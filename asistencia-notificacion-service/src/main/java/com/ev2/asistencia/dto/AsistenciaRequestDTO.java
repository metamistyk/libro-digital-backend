package com.ev2.asistencia.dto;

import com.ev2.asistencia.model.EstadoAsistencia;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AsistenciaRequestDTO {

    @NotNull
    private Long estudianteId;

    @NotNull
    private EstadoAsistencia estado;
}