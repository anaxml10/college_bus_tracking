package com.iccs.bustracking.controller;

import com.iccs.bustracking.model.Bus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DriverController {

    @GetMapping("/driver")
    public String driverPage(Model model) {
        model.addAttribute("buses", AdminController.busStore.values());
        return "driver";
    }

    @PostMapping("/api/driver/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestParam String busId, @RequestParam String password) {
        Bus bus = AdminController.busStore.get(busId);
        if (bus != null && bus.getPassword().equals(password)) {
            bus.setStatus("ACTIVE");
            return ResponseEntity.ok(bus);
        }
        return ResponseEntity.badRequest().body("{\"status\":\"ERROR\"}");
    }

    @PostMapping("/api/driver/updateLocation")
    @ResponseBody
    public ResponseEntity<?> updateLocation(@RequestParam String busId,
                                             @RequestParam double latitude,
                                             @RequestParam double longitude,
                                             @RequestParam double speed) {
        Bus bus = AdminController.busStore.get(busId);
        if (bus != null) {
            bus.setLatitude(latitude);
            bus.setLongitude(longitude);
            bus.setSpeed(speed);
            bus.setStatus("ACTIVE");
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/driver/reportBreakdown")
    @ResponseBody
    public ResponseEntity<?> reportBreakdown(@RequestParam String busId) {
        Bus bus = AdminController.busStore.get(busId);
        if (bus != null) {
            bus.setStatus("BREAKDOWN");
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/driver/endTrip")
    @ResponseBody
    public ResponseEntity<?> endTrip(@RequestParam String busId) {
        Bus bus = AdminController.busStore.get(busId);
        if (bus != null) {
            bus.setStatus("INACTIVE");
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}