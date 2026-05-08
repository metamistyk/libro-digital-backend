package com.ev2.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ev2.usuarios.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

}