package com.erosderiquedev.producer.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

import lombok.Data;
import java.time.Instant;
import java.util.Map;

@Data
public class LogMessage {
    private String applicationId;
    private String level;
    private String message;
    private Instant timestamp;
    private Map<String, String> metadata;
}
