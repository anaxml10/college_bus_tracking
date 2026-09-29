package com.iccs.bustracking.controller;

import com.iccs.bustracking.model.Bus;
import com.iccs.bustracking.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private TrackingService trackingService;

    @GetMapping("/admin")
    public String getAdminPortal(Model model) {
        model.addAttribute("buses", trackingService.getAllBuses());
        model.addAttribute("routes", trackingService.getAllRoutes());
        return "admin";
    }

    @ResponseBody
    @GetMapping("/api/bus/{busId}")
    public ResponseEntity<Bus> getBusDetails(@PathVariable String busId) {
        Bus bus = trackingService.getBusById(busId);
        if (bus != null) {
            return ResponseEntity.ok(bus);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseBody
    @GetMapping("/api/buses/all")
    public ResponseEntity<List<Bus>> getAllBusesApi() {
        return ResponseEntity.ok(trackingService.getAllBuses());
    }

    @PostMapping("/admin/add-bus")
    public String addBus(@ModelAttribute Bus bus) {
        bus.setStatus("INACTIVE");
        bus.setEstimatedTimeArrival("Not Active");
        trackingService.addBus(bus);
        return "redirect:/admin";
    }
}