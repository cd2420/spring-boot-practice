package com.example.point.repository;

import com.example.point.entity.Point;
import com.example.point.entity.PointReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointReservationRepository extends JpaRepository<PointReservation, Integer> {
}
