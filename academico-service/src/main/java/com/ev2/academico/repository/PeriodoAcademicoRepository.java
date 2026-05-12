package com.ev2.academico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ev2.academico.model.PeriodoAcademico;

public interface PeriodoAcademicoRepository extends JpaRepository<PeriodoAcademico, Long> {

    List<PeriodoAcademico> findByActivoTrue();
}