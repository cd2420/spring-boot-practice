package com.example.corona.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/event")
@RestController
public class APIEventController {

    @GetMapping("")
    public List<String> getEvents() {
        return List.of("event1", "event2");
    }

}
