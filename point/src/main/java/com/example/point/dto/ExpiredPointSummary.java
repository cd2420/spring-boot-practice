package com.example.point.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ExpiredPointSummary {
    String userId; // 유저ID
    Long amount;

    @QueryProjection
    public ExpiredPointSummary (
            String userId
            ,  Long amount
    ) {
        this.userId = userId;
        this.amount = amount;
    }

}
