package com.iccs.bustracking.controller;

import com.iccs.bustracking.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @Autowired
    private TrackingService trackingService;

    @GetMapping("/student")
    public String getStudentPortal(Model model) {
        model.addAttribute("buses", trackingService.getAllBuses());
        model.addAttribute("routes", trackingService.getAllRoutes());
        return "student";
    }
}