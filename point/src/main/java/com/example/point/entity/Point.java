package com.example.point.entity;


import com.example.point.entity.common.IdEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Point extends IdEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "point_wallet_id", nullable = false)
    PointWallet pointWallet;

    @Column(name = "amount", nullable = false, columnDefinition = "BIGINT")
    Long amount;

    @Column(name = "earned_date", nullable = false)
    LocalDate earnedDate;

    @Column(name = "expire_date", nullable = false)
    LocalDate expireDate;

    @Column(name = "is_used", nullable = false)
    Boolean used;

    @Column(name = "is_expired", nullable = false)
    Boolean expired;

    public Point(
      PointWallet pointWallet,
      Long amount,
      LocalDate earnedDate,
      LocalDate expireDate
    ) {
        this.pointWallet = pointWallet;
        this.amount = amount;
        this.earnedDate = earnedDate;
        this.expireDate = expireDate;
        this.used = false;
        this.expired = false;
    }

    public void expire() {
        if (!this.used) {
            this.expired = true;
        }
    }


}
