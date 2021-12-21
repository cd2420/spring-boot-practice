package com.example.corona.service;

import com.example.corona.constant.EventStatus;
import com.example.corona.domain.Event;
import com.example.corona.dto.EventDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class EventServiceImpl {

    public List<EventDto> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    ) {
        return List.of();
    }

    public Optional<EventDto> getEvent(Long eventId) {
        return Optional.empty();
    }

    public boolean createEvent(EventDto eventDto) {
        return true;
    }

    public boolean modifyEvent(Long eventId, EventDto eventDto) {
        return true;
    }

    public boolean removeEvent(Long eventId) {
        return true;
    }

}
