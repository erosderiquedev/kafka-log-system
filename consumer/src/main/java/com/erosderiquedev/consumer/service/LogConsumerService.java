package com.erosderiquedev.consumer.service;

import com.erosderiquedev.consumer.entity.LogEntity;
import com.erosderiquedev.consumer.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class LogConsumerService {

    private final LogRepository repository;

    @KafkaListener(topics = "application-logs", groupId = "log-group")
    public void consume(ConsumerRecord<String, LogEntity> record) {
        try {
            log.info("Recebida mensagem: {}", record);
            // Sua lógica de processamento aqui
            repository.save(record.value());

        } catch (Exception e) {
            log.error("Erro ao processar mensagem: {}", record, e);
            throw e; // Propaga o erro para o handler
        }
    }
}
