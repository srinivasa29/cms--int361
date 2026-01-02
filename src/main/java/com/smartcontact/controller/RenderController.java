package com.smartcontact.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("render")
@RestController
public class RenderController {

    @GetMapping("/")
    public String home() {
        return "SmartContactCRM is running successfully on Render 🚀";
    }
}
