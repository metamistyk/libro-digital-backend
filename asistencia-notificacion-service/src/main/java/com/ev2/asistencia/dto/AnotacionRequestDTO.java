package com.ev2.asistencia.dto;

import com.ev2.asistencia.model.TipoAnotacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnotacionRequestDTO {

    private Long estudianteId;

    private String descripcion;

    private TipoAnotacion tipo;
}