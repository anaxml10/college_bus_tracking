package com.iccs.bustracking.controller;

import com.iccs.bustracking.model.Bus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class AdminController {

    public static final Map<String, Bus> busStore = new ConcurrentHashMap<>();

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<Bus> busList = new ArrayList<>();
        if (busStore != null) {
            busList.addAll(busStore.values());
        }
        model.addAttribute("buses", busList);
        return "admin";
    }

    @PostMapping("/admin/addBus")
    @ResponseBody
    public ResponseEntity<?> addBus(@RequestParam(required = false, defaultValue = "BUS01") String busId,
                                    @RequestParam(required = false, defaultValue = "Driver") String driverName,
                                    @RequestParam(required = false, defaultValue = "1234") String password,
                                    @RequestParam(required = false, defaultValue = "KL-01") String regNumber,
                                    @RequestParam(required = false, defaultValue = "THRISSUR") String route) {
        try {
            String cleanId = busId.trim();
            Bus bus = new Bus(cleanId, driverName.trim(), password.trim(), regNumber.trim(), route.trim());
            bus.setStatus("INACTIVE");
            busStore.put(cleanId, bus);
            return ResponseEntity.ok().body("{\"status\":\"SUCCESS\"}");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("{\"status\":\"ERROR\"}");
        }
    }

    @PostMapping("/admin/deleteBus")
    @ResponseBody
    public ResponseEntity<?> deleteBus(@RequestParam String busId) {
        if (busId != null) {
            busStore.remove(busId.trim());
        }
        return ResponseEntity.ok().body("{\"status\":\"SUCCESS\"}");
    }
}