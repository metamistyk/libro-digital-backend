package com.ev2.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ev2.usuarios.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {

}