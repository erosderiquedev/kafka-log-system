package com.erosderiquedev.consumer.dto;

import lombok.Data;

@Data
public class FilterLogRequest {
    private String application_id;
    private String level;
}
