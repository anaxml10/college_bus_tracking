package com.iccs.bustracking.controller;

import com.iccs.bustracking.model.LocationUpdate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DriverController {

    public static Map<String, LocationUpdate> liveLocations = new ConcurrentHashMap<>();

    static {
        liveLocations.put("BUS01", new LocationUpdate("BUS01", "Santhosh", 10.3542, 76.2825, "INACTIVE", "1234"));
    }

    @GetMapping("/driver")
    public String driverPage() {
        return "driver";
    }

    @PostMapping("/api/driver/login-and-update")
    @ResponseBody
    public Map<String, Object> updateLocation(@RequestParam String busId, 
                                              @RequestParam String password, 
                                              @RequestParam double lat, 
                                              @RequestParam double lng,
                                              @RequestParam(defaultValue = "0.0") double speed) {
        Map<String, Object> res = new HashMap<>();
        LocationUpdate bus = liveLocations.get(busId);
        
        if (bus == null) {
            res.put("status", "ERROR");
            res.put("message", "Bus ID not registered by Admin!");
            return res;
        }
        
        if (!bus.getPassword().equals(password)) {
            res.put("status", "ERROR");
            res.put("message", "Invalid Security Password!");
            return res;
        }

        bus.setLatitude(lat);
        bus.setLongitude(lng);
        bus.setSpeed(speed);
        if (!"BREAKDOWN".equals(bus.getStatus())) {
            bus.setStatus("ACTIVE");
        }
        liveLocations.put(busId, bus);

        res.put("status", "SUCCESS");
        res.put("driverName", bus.getDriverName());
        res.put("busStatus", bus.getStatus());
        return res;
    }

    // ബ്രേക്ക്‌ഡൗൺ റിപ്പോർട്ട് ചെയ്യാൻ
    @PostMapping("/api/driver/breakdown")
    @ResponseBody
    public String reportBreakdown(@RequestParam String busId, @RequestParam String password) {
        LocationUpdate bus = liveLocations.get(busId);
        if (bus != null && bus.getPassword().equals(password)) {
            bus.setStatus("BREAKDOWN");
            return "SUCCESS";
        }
        return "FAILED";
    }

    // ബ്രേക്ക്‌ഡൗൺ മാറി വീണ്ടും യാത്ര തുടരാൻ (Resolve Breakdown)
    @PostMapping("/api/driver/resolve-breakdown")
    @ResponseBody
    public String resolveBreakdown(@RequestParam String busId, @RequestParam String password) {
        LocationUpdate bus = liveLocations.get(busId);
        if (bus != null && bus.getPassword().equals(password)) {
            bus.setStatus("ACTIVE");
            return "SUCCESS";
        }
        return "FAILED";
    }
}