package com.ev2.asistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ev2.asistencia.model.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

}