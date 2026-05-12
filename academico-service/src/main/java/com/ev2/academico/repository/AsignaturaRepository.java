package com.ev2.academico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ev2.academico.model.Asignatura;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {

    List<Asignatura> findByCursoId(Long cursoId);
}