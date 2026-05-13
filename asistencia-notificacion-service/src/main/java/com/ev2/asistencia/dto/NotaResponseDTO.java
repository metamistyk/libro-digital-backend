package com.ev2.asistencia.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotaResponseDTO {

    private Long id;

    private Long estudianteId;

    private Long asignaturaId;

    private Double nota;

    private String descripcion;

    private LocalDateTime fechaRegistro;
}