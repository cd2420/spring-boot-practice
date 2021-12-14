package com.example.corona.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/place")
@Controller
public class PlaceController {

    @GetMapping("/")
    public String login() {
        return "place/index";
    }

    @GetMapping("/{placeId}")
    public String sign(
            @PathVariable final Integer placeId
    ) {
        return "place/detail";
    }
}
