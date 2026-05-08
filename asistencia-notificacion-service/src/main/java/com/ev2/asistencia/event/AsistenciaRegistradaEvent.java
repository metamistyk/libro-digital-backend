package com.ev2.asistencia.event;

import java.time.LocalDateTime;

public class AsistenciaRegistradaEvent {

    private Long asistenciaId;
    private Long estudianteId;
    private LocalDateTime fechaHora;
    private String estado;

    public AsistenciaRegistradaEvent() {
    }

    public AsistenciaRegistradaEvent(Long asistenciaId, Long estudianteId, LocalDateTime fechaHora, String estado) {
        this.asistenciaId = asistenciaId;
        this.estudianteId = estudianteId;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    public Long getAsistenciaId() {
        return asistenciaId;
    }

    public void setAsistenciaId(Long asistenciaId) {
        this.asistenciaId = asistenciaId;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}