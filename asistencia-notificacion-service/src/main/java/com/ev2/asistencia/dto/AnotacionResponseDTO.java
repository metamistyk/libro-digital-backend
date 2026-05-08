package com.ev2.asistencia.dto;

import java.time.LocalDateTime;

import com.ev2.asistencia.model.TipoAnotacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnotacionResponseDTO {

    private Long id;

    private Long estudianteId;

    private String descripcion;

    private LocalDateTime fechaCreacion;

    private TipoAnotacion tipo;
}