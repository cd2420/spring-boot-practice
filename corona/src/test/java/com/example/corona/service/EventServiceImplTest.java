package com.example.corona.service;

import com.example.corona.constant.EventStatus;
import com.example.corona.dto.EventDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EventServiceImplTest {

    private EventServiceImpl sut;

    @DisplayName("검색 조건 없이 이벤트 검색하면, 전체 결과를 출력하여 보여준다.")
    @Test
    void givenNothing_whenSearchingEvents_thenReturnsEntireEventList() {
        List<EventDto> list = sut.getEvents(null
                , null
                , null
                , null
                , null);

        assertThat(list).hasSize(2);
    }

    @DisplayName("검색 조건 함께 이벤트 검색하면, 전체 결과를 출력하여 보여준다.")
    @Test
    void givenSearchParams_whenSearchingEvents_thenReturnsEntireEventList() {

        Long placeId = 1L;
        String eventName = "오전 운동";
        EventStatus eventStatus = EventStatus.OPENED;
        LocalDateTime eventStartDatetime = LocalDateTime.of(2021, 1, 1, 0, 0);
        LocalDateTime eventEndDatetime = LocalDateTime.of(2021, 1, 2, 0, 0);


        List<EventDto> list = sut.getEvents(placeId
                , eventName
                , eventStatus
                , eventStartDatetime
                , eventEndDatetime );

        assertThat(list)
                .hasSize(1)
                .allSatisfy(event -> {
                    assertThat(event)
                            .hasFieldOrPropertyWithValue("placeId", placeId)
                            .hasFieldOrPropertyWithValue("eventName", eventName)
                            .hasFieldOrPropertyWithValue("eventStatus", eventStatus);
                    assertThat(event.getEventStartDatetime()).isAfterOrEqualTo(eventStartDatetime);
                    assertThat(event.getEventStartDatetime()).isBeforeOrEqualTo(eventStartDatetime);
                });
    }

}