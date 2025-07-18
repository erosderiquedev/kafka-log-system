package com.erosderiquedev.producer.dto;

import java.util.Map;

@lombok.Data
public class LogRequest {
    private String application_id;
    private String level;
    private String message;
    private Map<String, String> metadata;
}