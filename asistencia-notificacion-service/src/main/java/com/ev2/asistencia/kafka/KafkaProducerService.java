package com.ev2.asistencia.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ev2.asistencia.event.AsistenciaRegistradaEvent;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, AsistenciaRegistradaEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, AsistenciaRegistradaEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarAsistenciaRegistrada(AsistenciaRegistradaEvent event) {
        kafkaTemplate.send("asistencia-registrada", event);
    }
}