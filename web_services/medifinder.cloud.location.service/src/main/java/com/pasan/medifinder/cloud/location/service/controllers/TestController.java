package com.pasan.medifinder.cloud.location.service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("locations")
public class TestController {

    @GetMapping()
    public String Test() {
        return "Hello world from location service";
    }
}