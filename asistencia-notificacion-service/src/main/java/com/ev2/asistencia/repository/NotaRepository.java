package com.ev2.asistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ev2.asistencia.model.Nota;

public interface NotaRepository
        extends JpaRepository<Nota, Long> {

    List<Nota> findByEstudianteId(Long estudianteId);
}