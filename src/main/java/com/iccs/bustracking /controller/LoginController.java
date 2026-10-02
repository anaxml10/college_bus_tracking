package com.iccs.bustracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Direct Root mapping to index.html login page
    @GetMapping({"/", "/login", "/index"})
    public String index() {
        return "index";
    }
}