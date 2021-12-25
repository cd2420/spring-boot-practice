package com.example.point.repository;

import com.example.point.entity.Point;
import com.example.point.entity.PointWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointWalletRepository extends JpaRepository<PointWallet, Long> {
}
