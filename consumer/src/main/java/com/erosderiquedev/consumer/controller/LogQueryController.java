package com.erosderiquedev.consumer.controller;


import com.erosderiquedev.consumer.dto.FilterLogRequest;
import com.erosderiquedev.consumer.entity.LogEntity;
import com.erosderiquedev.consumer.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogQueryController {

    private final LogRepository repository;

    @GetMapping
    public ResponseEntity<List<LogEntity>> getAllLogs() {
        return  ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<LogEntity>> filterLogs(
            @RequestBody FilterLogRequest filterLogRequest
    ) {

        if (filterLogRequest.getLevel() != null && filterLogRequest.getApplication_id() != null) {
            return ResponseEntity.ok(
                    repository.findByLevelAndApplicationId(
                            filterLogRequest.getLevel(),
                            filterLogRequest.getApplication_id()
                    )
            );
        } else if (filterLogRequest.getLevel() != null) {
            return  ResponseEntity.ok(
                    repository.findByLevel(filterLogRequest.getLevel()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
