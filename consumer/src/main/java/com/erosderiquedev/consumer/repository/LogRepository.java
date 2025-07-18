package com.erosderiquedev.consumer.repository;

import com.erosderiquedev.consumer.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<LogEntity, Long> {
    List<LogEntity> findByLevel(String level);
    List<LogEntity> findByApplicationId(String applicationId);
    List<LogEntity> findByLevelAndApplicationId(String level, String applicationId);
}
