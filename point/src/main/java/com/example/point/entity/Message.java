package com.example.point.entity;

import com.example.point.entity.common.IdEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Message extends IdEntity {

    @Column(name = "user_id", nullable = false)
    String userId;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "content", nullable = false)
    String content;

    public static Message expiredPointMessageInstance(
            String userId, LocalDate today, Long amount
    ) {
        return new Message(
                userId,
                String.format("%s 포인트 만료", amount.toString()),
                String.format("%s 기준 %s 포인트가 만료되었습니다.", today.format(DateTimeFormatter.ISO_DATE), amount)
        );
    }
}
