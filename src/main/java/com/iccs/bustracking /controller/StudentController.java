package com.iccs.bustracking.controller;

import com.iccs.bustracking.model.Bus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class StudentController {

    @GetMapping("/student")
    public String studentPage(Model model) {
        model.addAttribute("buses", new ArrayList<>(AdminController.busStore.values()));
        return "student";
    }

    @GetMapping("/api/bus/{busId}")
    @ResponseBody
    public ResponseEntity<?> getBusDetails(@PathVariable String busId) {
        Bus bus = AdminController.busStore.get(busId);
        if (bus != null) {
            return ResponseEntity.ok(bus);
        }
        return ResponseEntity.notFound().build();
    }
}