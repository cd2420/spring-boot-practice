package com.example.point.job.reservation;

import com.example.point.repository.Point;
import com.example.point.entity.PointReservation;
import com.example.point.entity.PointWallet;
import com.example.point.repository.PointRepository;
import com.example.point.repository.PointReservationRepository;
import com.example.point.repository.PointWalletRepository;
import com.mysema.commons.lang.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.Map;

@Configuration
@Slf4j
public class ExecutePointReservationStepConfiguration {

    @Bean
    @JobScope
    public Step executePointReservationStep (
            StepBuilderFactory stepBuilderFactory,
            PlatformTransactionManager platformTransactionManager,
            JpaPagingItemReader<PointReservation> executePointReservationItemReader,
            ItemProcessor<PointReservation, Pair<PointReservation, Point>> executePointReservationItemProcessor,
            ItemWriter<Pair<PointReservation, Point>> executePointReservationItemWriter
    ) {
        return stepBuilderFactory
                .get("executePointReservationStep")
                .allowStartIfComplete(true)
                .transactionManager(platformTransactionManager)
                .<PointReservation,  Pair<PointReservation, Point>>chunk(1000)
                .reader(executePointReservationItemReader)
                .processor(executePointReservationItemProcessor)
                .writer(executePointReservationItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<PointReservation> executePointReservationItemReader(
            EntityManagerFactory entityManagerFactory,
            @Value("#{T(java.time.LocalDate).parse(jobParameters[today])}")
                    LocalDate today
    ) {
        return new JpaPagingItemReaderBuilder<PointReservation>()
                .name("executePointReservationItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select pr from PointReservation pr where pr.earnedDate = :today and pr.executed = false")
                .parameterValues(Map.of("today", today))
                .pageSize(1000)
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<PointReservation, Pair<PointReservation, Point>> executePointReservationItemProcessor() {
        return reservation -> {
            reservation.setExecuted(true);
            Point earnedPoint = new Point(
                    reservation.getPointWallet(),
                    reservation.getAmount(),
                    reservation.getEarnedDate(),
                    reservation.getExpireDate()
            );
            PointWallet wallet = reservation.getPointWallet();
            wallet.setAmount(wallet.getAmount() + earnedPoint.getAmount());
            return Pair.of(reservation, earnedPoint);
        };
    }

    @Bean
    @StepScope
    public ItemWriter<Pair<PointReservation, Point>> executePointReservationItemWriter(
            PointReservationRepository pointReservationRepository,
            PointRepository pointRepository,
            PointWalletRepository pointWalletRepository
    ) {
        return reservationAndPointPairs -> {
            for (Pair<PointReservation, Point> pair: reservationAndPointPairs) {
                PointReservation reservation = pair.getFirst();
                pointReservationRepository.save(reservation);
                Point point = pair.getSecond();
                pointRepository.save(point);
                pointWalletRepository.save(point.getPointWallet());
            }
        };
    }
}
