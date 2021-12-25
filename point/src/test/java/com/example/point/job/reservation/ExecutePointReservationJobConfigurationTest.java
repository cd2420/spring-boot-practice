package com.example.point.job.reservation;

import com.example.point.BatchTestSupport;
import com.example.point.entity.Point;
import com.example.point.entity.PointReservation;
import com.example.point.entity.PointWallet;
import com.example.point.repository.PointRepository;
import com.example.point.repository.PointReservationRepository;
import com.example.point.repository.PointWalletRepository;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class ExecutePointReservationJobConfigurationTest extends BatchTestSupport {

    @Autowired
    PointWalletRepository pointWalletRepository;

    @Autowired
    PointReservationRepository pointReservationRepository;

    @Autowired
    Job executePointReservationJob;

    @Autowired
    PointRepository pointRepository;

    @Test
    void executePointReservationJob() throws Exception {
        // given
        // point reservation이 있어야함
        LocalDate earnDate = LocalDate.of(2021, 12, 15);

        PointWallet pointWallet = pointWalletRepository.save(
                new PointWallet(
                        "user1"
                        , Long.valueOf(3000)
                )
        );

        pointReservationRepository.save(
                new PointReservation(
                        pointWallet
                        , Long.valueOf(1000)
                        , earnDate
                        , 10
                )
        );
        // when
        // executePointReservationJob을 실행시킴.
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("today", "2021-12-15")
                .toJobParameters();

        JobExecution jobExecution = launchJob(executePointReservationJob, jobParameters);

        // then
        // point reservation은 완료처리되야함
        // point 적립이 생겨야함.
        // point wallet의 잔액이 증가해야함.
        then(jobExecution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
        List<PointReservation> reservations = pointReservationRepository.findAll();
        then(reservations).hasSize(1);
        then(reservations.get(0).getExecuted()).isTrue();

        List<Point> points = pointRepository.findAll();
        then(points).hasSize(1);
        then(points.get(0).getAmount()).isEqualByComparingTo(Long.valueOf(1000));
        then(points.get(0).getEarnedDate()).isEqualTo(LocalDate.of(2021, 12, 15));
        then(points.get(0).getExpireDate()).isEqualTo(LocalDate.of(2021, 12, 25));

        List<PointWallet> wallets = pointWalletRepository.findAll();
        then(wallets).hasSize(1);
        then(wallets.get(0).getAmount()).isEqualByComparingTo(Long.valueOf(4000));
    }
}