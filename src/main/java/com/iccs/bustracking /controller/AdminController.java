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
    public static final Map<String, List<String>> busStopsMap = new ConcurrentHashMap<>();
    public static final Map<String, String> studentStore = new ConcurrentHashMap<>(); // Student Database Map

    static {
        // Default Students
        studentStore.put("anax", "1234");
        studentStore.put("student01", "1234");
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<Bus> busList = new ArrayList<>(busStore.values());
        model.addAttribute("buses", busList);
        model.addAttribute("students", studentStore);
        return "admin";
    }

    @PostMapping("/admin/addBus")
    @ResponseBody
    public ResponseEntity<?> addBus(@RequestParam String busId,
                                    @RequestParam String driverName,
                                    @RequestParam String password,
                                    @RequestParam String regNumber,
                                    @RequestParam String route,
                                    @RequestParam(value = "stops", required = false) List<String> stops) {
        try {
            String cleanId = busId.trim().toUpperCase();
            Bus bus = new Bus(cleanId, driverName.trim(), password.trim(), regNumber.trim().toUpperCase(), route.trim());
            bus.setStatus("INACTIVE");
            busStore.put(cleanId, bus);
            
            if (stops != null) {
                busStopsMap.put(cleanId, stops);
            } else {
                busStopsMap.put(cleanId, new ArrayList<>());
            }
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
            busStopsMap.remove(busId.trim());
        }
        return ResponseEntity.ok().body("{\"status\":\"SUCCESS\"}");
    }

    // Add Student API
    @PostMapping("/admin/addStudent")
    @ResponseBody
    public ResponseEntity<?> addStudent(@RequestParam String username, @RequestParam String password) {
        if (username != null && !username.trim().isEmpty() && password != null) {
            studentStore.put(username.trim().toLowerCase(), password.trim());
            return ResponseEntity.ok().body("{\"status\":\"SUCCESS\"}");
        }
        return ResponseEntity.badRequest().body("{\"status\":\"ERROR\"}");
    }

    // Student Authentication API for Student Portal
    @PostMapping("/api/student/login")
    @ResponseBody
    public ResponseEntity<?> studentLogin(@RequestParam String username, @RequestParam String password) {
        String cleanUser = username.trim().toLowerCase();
        if (studentStore.containsKey(cleanUser) && studentStore.get(cleanUser).equals(password.trim())) {
            return ResponseEntity.ok().body("{\"status\":\"SUCCESS\"}");
        }
        return ResponseEntity.status(401).body("{\"status\":\"INVALID\"}");
    }

    // Driver SOS Alert Update API
    @PostMapping("/api/bus/updateAlert")
    @ResponseBody
    public ResponseEntity<?> updateAlert(@RequestParam String busId, @RequestParam String status) {
        String cleanId = busId.trim().toUpperCase();
        if (busStore.containsKey(cleanId)) {
            busStore.get(cleanId).setStatus(status.trim().toUpperCase());
            return ResponseEntity.ok().body("{\"status\":\"SUCCESS\"}");
        }
        return ResponseEntity.badRequest().body("{\"status\":\"ERROR\"}");
    }
}