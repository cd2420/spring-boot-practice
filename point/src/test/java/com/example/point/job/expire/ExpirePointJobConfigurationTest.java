package com.example.point.job.expire;

import com.example.point.BatchTestSupport;
import com.example.point.repository.Point;
import com.example.point.entity.PointWallet;
import com.example.point.repository.PointRepository;
import com.example.point.repository.PointWalletRepository;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class ExpirePointJobConfigurationTest extends BatchTestSupport {

    @Autowired
    Job expirePointJob;

    @Autowired
    PointWalletRepository pointWalletRepository;

    @Autowired
    PointRepository pointRepository;

    @Test
    void expirePointJob() throws Exception {
        //given
        LocalDate earnDate = LocalDate.of(2021, 12, 25);
        LocalDate expireDate = LocalDate.of(2021,12,26);
        PointWallet pointWallet = pointWalletRepository.save(
                new PointWallet(
                        "user123",
                        Long.valueOf(6000)
                )
        );
        pointRepository.save(new Point(pointWallet, Long.valueOf(1000), earnDate, expireDate));
        pointRepository.save(new Point(pointWallet, Long.valueOf(1000), earnDate, expireDate));
        pointRepository.save(new Point(pointWallet, Long.valueOf(1000), earnDate, expireDate));

        //when
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("today", "2021-12-27")
                .addLong("currTime", System.currentTimeMillis())
                .toJobParameters();
        JobExecution jobExecution = launchJob(expirePointJob, jobParameters);

        //then
        List<Point> points = pointRepository.findAll();
        then(jobExecution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
        then(points.stream().filter(Point::expire)).hasSize(3);
        PointWallet changePointWallet = pointWalletRepository.findById(pointWallet.getId()).orElseGet(null);
        then(changePointWallet).isNotNull();
        then(changePointWallet.getAmount()).isEqualByComparingTo(Long.valueOf(3000));

    }
}