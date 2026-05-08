package com.ev2.academico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ev2.academico.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}