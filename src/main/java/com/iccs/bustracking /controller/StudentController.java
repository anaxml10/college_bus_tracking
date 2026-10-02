package com.iccs.bustracking.controller;

import com.iccs.bustracking.model.Bus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    @GetMapping("/student")
    public String studentPage(Model model) {
        List<Bus> busList = new ArrayList<>();
        if (AdminController.busStore != null && !AdminController.busStore.isEmpty()) {
            busList.addAll(AdminController.busStore.values());
        }
        model.addAttribute("buses", busList);
        return "student";
    }

    @GetMapping("/api/bus/{busId}")
    @ResponseBody
    public ResponseEntity<Bus> getBusDetails(@PathVariable String busId) {
        if (AdminController.busStore != null && AdminController.busStore.containsKey(busId)) {
            return ResponseEntity.ok(AdminController.busStore.get(busId));
        }
        return ResponseEntity.notFound().build();
    }
}