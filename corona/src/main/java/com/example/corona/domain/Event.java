package com.example.corona.domain;

import com.example.corona.constant.EventStatus;
import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class Event {

    @Id
    private Long id;

    private Long placeId;
    private String eventName;
    private EventStatus eventStatus;
    private LocalDateTime eventStartDatetime;
    private LocalDateTime eventEndDatetime;
    private Integer currentNumberOfPeople;
    private Integer capacity;
    private String memo;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
