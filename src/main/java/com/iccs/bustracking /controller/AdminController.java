package com.iccs.bustracking.controller;

import com.iccs.bustracking.model.LocationUpdate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminPage(Model model) {
        if (DriverController.liveLocations.isEmpty()) {
            DriverController.liveLocations.put("BUS01", new LocationUpdate("BUS01", "Santhosh", 10.3542, 76.2825, "INACTIVE", "1234"));
        }
        model.addAttribute("buses", new ArrayList<>(DriverController.liveLocations.values()));
        return "admin";
    }

    @PostMapping("/admin/add-bus")
    public String addBus(@RequestParam String busId, 
                         @RequestParam String driverName,
                         @RequestParam String password) {
        LocationUpdate bus = new LocationUpdate(busId, driverName, 10.3542, 76.2825, "INACTIVE", password);
        DriverController.liveLocations.put(busId, bus);
        return "redirect:/admin";
    }
}