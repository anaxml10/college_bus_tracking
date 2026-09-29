package com.iccs.bustracking.controller;

import com.iccs.bustracking.model.LocationUpdate;
import com.iccs.bustracking.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DriverController {

    @Autowired
    private TrackingService trackingService;

    @GetMapping("/driver")
    public String getDriverPortal(Model model) {
        model.addAttribute("buses", trackingService.getAllBuses());
        return "driver";
    }

    @ResponseBody
    @PostMapping("/api/driver/update-location")
    public ResponseEntity<?> updateLocationTelemetry(@RequestBody LocationUpdate update) {
        boolean updated = trackingService.updateLocation(update.getBusId(), update.getLatitude(), update.getLongitude());
        if (updated) {
            return ResponseEntity.ok().body("{\"status\": \"success\", \"message\": \"GPS Telemetry Updated Successfully\"}");
        }
        return ResponseEntity.badRequest().body("{\"status\": \"error\", \"message\": \"Bus ID not found\"}");
    }

    @ResponseBody
    @PostMapping("/api/driver/status")
    public ResponseEntity<?> updateTripStatus(@RequestParam String busId, @RequestParam String status) {
        boolean updated = trackingService.updateTripStatus(busId, status);
        if (updated) {
            return ResponseEntity.ok().body("{\"status\": \"success\", \"message\": \"Trip Status Updated\"}");
        }
        return ResponseEntity.badRequest().body("{\"status\": \"error\", \"message\": \"Invalid Request\"}");
    }
}