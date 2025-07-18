package com.erosderiquedev.producer.service;

import com.erosderiquedev.producer.model.LogMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, LogMessage> kafkaTemplate;

    public void sendLog(LogMessage logMessage) {
        kafkaTemplate.send("application-logs", logMessage.getLevel(), logMessage)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("""
                    Falha ao enviar mensagem:\s
                    Tópico: {}
                    Nível: {}
                    Mensagem: {}""",
                                "application-logs",
                                logMessage.getLevel(),
                                logMessage.getMessage(),
                                ex);
                    } else {
                        log.info("""
                    Mensagem enviada com sucesso:
                    Tópico: {}
                    Partição: {}
                    Offset: {}""",
                                result.getRecordMetadata().topic(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    }
                });
    }
}
