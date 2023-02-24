package com.pasan.medifinder.cloud.admin.service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admins")
public class TestController {

    @GetMapping()
    public String Test() {
        return "Hello world from admin service";
    }
}