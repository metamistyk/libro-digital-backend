package com.ev2.academico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ev2.academico.model.AsignacionDocente;

public interface AsignacionDocenteRepository extends JpaRepository<AsignacionDocente, Long> {

    List<AsignacionDocente> findByDocenteId(Long docenteId);

    List<AsignacionDocente> findByCursoId(Long cursoId);

    List<AsignacionDocente> findByAsignaturaId(Long asignaturaId);
}