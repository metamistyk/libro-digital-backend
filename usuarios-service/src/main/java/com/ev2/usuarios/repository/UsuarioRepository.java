package com.ev2.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ev2.usuarios.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}