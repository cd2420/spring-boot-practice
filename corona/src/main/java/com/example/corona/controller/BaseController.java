package com.example.corona.controller;

import com.example.corona.exception.GeneralException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

    @GetMapping("/error")
    public String root() {
        return "index";
    }
}
