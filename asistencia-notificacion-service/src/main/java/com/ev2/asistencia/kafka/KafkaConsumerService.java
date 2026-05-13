package com.ev2.asistencia.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ev2.asistencia.event.AsistenciaRegistradaEvent;
import com.ev2.asistencia.service.NotificacionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final NotificacionService notificacionService;

    @KafkaListener(
            topics = "asistencia-registrada",
            groupId = "notificacion-group")
    public void recibirAsistenciaRegistrada(AsistenciaRegistradaEvent event) {

        notificacionService.crearDesdeAsistenciaRegistrada(event);
    }
}