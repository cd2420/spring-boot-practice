package com.example.point.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.point.dto.QExpiredPointSummary is a Querydsl Projection type for ExpiredPointSummary
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QExpiredPointSummary extends ConstructorExpression<ExpiredPointSummary> {

    private static final long serialVersionUID = -358743427L;

    public QExpiredPointSummary(com.querydsl.core.types.Expression<String> userId, com.querydsl.core.types.Expression<Long> amount) {
        super(ExpiredPointSummary.class, new Class<?>[]{String.class, long.class}, userId, amount);
    }

}

