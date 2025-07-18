package com.erosderiquedev.producer.controller;


import com.erosderiquedev.producer.dto.LogRequest;
import com.erosderiquedev.producer.model.LogMessage;
import com.erosderiquedev.producer.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogController {

    private final KafkaProducerService producerService;

    @PostMapping
    public ResponseEntity<String> sendLog(@RequestBody LogRequest request) {
        LogMessage logMessage = new LogMessage();
        logMessage.setApplicationId(request.getApplication_id());
        logMessage.setLevel(request.getLevel());
        logMessage.setMessage(request.getMessage());
        logMessage.setTimestamp(Instant.now());
        logMessage.setMetadata(request.getMetadata());
        producerService.sendLog(logMessage);
        return ResponseEntity.ok("Log message send successfully to kafka topic");
    }
}
