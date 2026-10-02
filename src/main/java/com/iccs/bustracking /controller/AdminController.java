package com.iccs.bustracking.controller;

import com.iccs.bustracking.model.Bus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class AdminController {

    public static Map<String, Bus> busStore = new ConcurrentHashMap<>();

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("buses", new ArrayList<>(busStore.values()));
        return "admin";
    }

    @PostMapping("/admin/addBus")
    @ResponseBody
    public ResponseEntity<?> addBus(@RequestParam String busId,
                                      @RequestParam String driverName,
                                      @RequestParam String password,
                                      @RequestParam String regNumber,
                                      @RequestParam String route) {
        Bus newBus = new Bus(busId.trim(), driverName.trim(), password.trim(), regNumber.trim(), route.trim());
        busStore.put(busId.trim(), newBus);
        return ResponseEntity.ok(newBus);
    }

    @PostMapping("/admin/deleteBus")
    @ResponseBody
    public ResponseEntity<?> deleteBus(@RequestParam String busId) {
        if (busId != null) {
            busStore.remove(busId.trim());
        }
        return ResponseEntity.ok("{\"status\":\"SUCCESS\"}");
    }
}