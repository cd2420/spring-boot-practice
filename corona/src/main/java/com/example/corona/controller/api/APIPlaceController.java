package com.example.corona.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/place")
@RestController
public class APIPlaceController {

    @GetMapping("")
    public String getPlaces() {
        return "places";
    }

}
