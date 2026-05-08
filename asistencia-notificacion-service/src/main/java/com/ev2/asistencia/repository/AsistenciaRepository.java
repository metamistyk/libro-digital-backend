package com.ev2.asistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ev2.asistencia.model.Asistencia;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

}