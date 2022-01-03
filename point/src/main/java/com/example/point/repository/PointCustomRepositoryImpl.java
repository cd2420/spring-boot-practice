package com.example.point.repository;

import com.example.point.dto.ExpiredPointSummary;
import com.example.point.dto.QExpiredPointSummary;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;

public class PointCustomRepositoryImpl extends QuerydslRepositorySupport implements PointCustomRepository {

    public PointCustomRepositoryImpl() {
        super(Point.class);
    }

    @Override
    public Page<ExpiredPointSummary> sumByExpiredDate(LocalDate alarmCriteriaDate, Pageable pageable) {
//        return  null;
        QPoint point = QPoint.point;
        JPQLQuery<ExpiredPointSummary> query = from(point)
                .select(
                        new QExpiredPointSummary(
                                point.pointWallet.userId
                                , point.amount.sum().coalesce(Long.valueOf(0))
                        )

                )
                .where(point.expired.eq(true))
                .where(point.used.eq(false))
                .where(point.expireDate.eq(alarmCriteriaDate))
                .groupBy(point.pointWallet)
                ;

        long elementCount = query.fetchCount();
        List<ExpiredPointSummary> expiredPointSummaryList = getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<>(
                expiredPointSummaryList,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()),
                elementCount);

    }
}
