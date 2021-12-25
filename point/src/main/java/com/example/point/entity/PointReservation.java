package com.example.point.entity;

import com.example.point.entity.common.IdEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class PointReservation extends IdEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "point_wallet_id", nullable = false)
    PointWallet pointWallet;

    @Column(name = "amount", nullable = false)
    Long amount;

    @Column(name = "earned_date", nullable = false)
    LocalDate earnedDate;

    @Column(name = "available_days", nullable = false)
    Integer availableDays;

    @Setter
    @Column(name = "is_executed")
    Boolean executed;

    public PointReservation(
            PointWallet pointWallet,
            Long amount,
            LocalDate earnedDate,
            Integer availableDays
    ) {
        this.pointWallet = pointWallet;
        this.amount = amount;
        this.earnedDate = earnedDate;
        this.availableDays = availableDays;
        this.executed = false;
    }

    public LocalDate getExpireDate() {
        return this.earnedDate.plusDays(this.availableDays);
    }
}
