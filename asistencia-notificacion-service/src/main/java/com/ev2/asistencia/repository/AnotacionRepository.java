package com.ev2.asistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ev2.asistencia.model.Anotacion;

public interface AnotacionRepository extends JpaRepository<Anotacion, Long> {

}