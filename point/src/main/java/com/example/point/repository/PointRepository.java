package com.example.point.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long>, PointCustomRepository {
}
