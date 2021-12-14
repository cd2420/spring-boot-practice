package com.example.corona.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/event")
@Controller
public class EventController {

    @GetMapping("/")
    public String login() {
        return "event/index";
    }

    @GetMapping("/{eventId}")
    public String sign(
            @PathVariable final Integer placeId
    ) {
        return "event/detail";
    }
}
