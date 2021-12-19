package com.example.corona.controller.api;

import com.example.corona.constant.ErrorCode;
import com.example.corona.dto.APIErrorResponse;
import com.example.corona.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/event")
@RestController
public class APIEventController {

    @GetMapping("")
    public List<String> getEvents() {
        return List.of("event1", "event2");
    }

    @PostMapping("")
    public Boolean createEvent() {
        return true;
    }

    @GetMapping("/{eventId}")
    public String getEvent(
            @PathVariable final Integer eventId
    ) {
        return "event" + eventId;
    }
}
