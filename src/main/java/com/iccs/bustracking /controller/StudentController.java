package com.iccs.bustracking.controller;

import com.iccs.bustracking.model.LocationUpdate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    @GetMapping("/student")
    public String studentPage(Model model) {
        List<LocationUpdate> busList = new ArrayList<>(DriverController.liveLocations.values());
        
        if (busList.isEmpty()) {
            busList.add(new LocationUpdate("BUS01", 10.3542, 76.2825, "INACTIVE", "1234"));
        }
        
        model.addAttribute("buses", busList);
        return "student";
    }

    @GetMapping("/api/bus/{busId}")
    @ResponseBody
    public LocationUpdate getBusLocation(@PathVariable String busId) {
        return DriverController.liveLocations.getOrDefault(busId, new LocationUpdate(busId, 10.3542, 76.2825, "INACTIVE", "1234"));
    }
}