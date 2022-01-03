package com.example.point.repository;

import com.example.point.dto.ExpiredPointSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface PointCustomRepository {
    Page<ExpiredPointSummary> sumByExpiredDate(LocalDate alarmCriteriaDate, Pageable pageable);
}
