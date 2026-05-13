package com.ev2.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ev2.usuarios.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {

    boolean existsByNombre(String nombre);

    Optional<Rol> findByNombre(String nombre);
}