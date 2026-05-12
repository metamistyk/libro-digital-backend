package com.ev2.asistencia.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ev2.asistencia.event.AsistenciaRegistradaEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private static final String TOPIC_ASISTENCIA_REGISTRADA = "asistencia-registrada";

    private final KafkaTemplate<String, AsistenciaRegistradaEvent> kafkaTemplate;

    public void enviarAsistenciaRegistrada(AsistenciaRegistradaEvent event) {
        kafkaTemplate.send(TOPIC_ASISTENCIA_REGISTRADA, event);
    }
}