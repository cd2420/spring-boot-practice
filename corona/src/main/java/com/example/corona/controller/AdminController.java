package com.example.corona.controller;

import com.example.corona.constant.PlaceType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @GetMapping(value = "/places")
    public ModelAndView adminPlaces(
       PlaceType placeType,
       String placeName,
       String address
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("placeType", placeType);
        map.put("placeName", placeName);
        map.put("address", address);
        return new ModelAndView("admin/places", map);
    }

    @GetMapping("/places/{placeId}")
    public String detailPlace(
            @PathVariable final Integer placeId
    ) {
        return "admin/place-detail";
    }

    @GetMapping("/events")
    public String events() {
        return "admin/events";
    }

    @GetMapping("/events/{eventId}")
    public String detailEvent(
            @PathVariable final Integer eventId
    ) {
        return "admin/event-detail";
    }
}
