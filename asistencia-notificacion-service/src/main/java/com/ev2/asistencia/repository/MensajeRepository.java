package com.ev2.asistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ev2.asistencia.model.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    // Mensajes recibidos por un usuario
    List<Mensaje> findByDestinatarioId(Long destinatarioId);

    // Conversación entre dos usuarios
    List<Mensaje> findByRemitenteIdAndDestinatarioIdOrRemitenteIdAndDestinatarioId(
        Long remitente1, Long destinatario1,
        Long remitente2, Long destinatario2);
}