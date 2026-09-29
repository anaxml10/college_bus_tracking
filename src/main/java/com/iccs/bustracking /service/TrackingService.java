package com.iccs.bustracking.service;

import com.iccs.bustracking.model.Bus;
import com.iccs.bustracking.model.Route;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TrackingService {

    private final Map<String, Bus> busRepository = new ConcurrentHashMap<>();
    private final Map<String, Route> routeRepository = new ConcurrentHashMap<>();

    public TrackingService() {
        // Initializing Routes based on project document
        routeRepository.put("R01", new Route("R01", "Route A (North Campus)", "Thrissur Town", "ICCS Campus", Arrays.asList("Swaraj Round", "Pvt Bus Stand", "Olarikkara", "ICCS")));
        routeRepository.put("R02", new Route("R02", "Route B (City Center)", "Irinjalakuda", "ICCS Campus", Arrays.asList("KSRTC Station", "Chalaakudy Road", "Rappal", "ICCS")));
        routeRepository.put("R03", new Route("R03", "Route C (Suburbs)", "Pudukad", "ICCS Campus", Arrays.asList("Pudukad Junction", "Nanthipulam", "Mupliyam", "ICCS")));

        // Initializing Buses based on project document
        busRepository.put("BUS01", new Bus("BUS01", "KL-08-AX-1001", "Rajesh Kumar", "Route A (North Campus)", 10.3542, 76.2825, "ON TRIP", "12 Mins Saved (ML Dynamic)", "PAID"));
        busRepository.put("BUS02", new Bus("BUS02", "KL-08-AX-2002", "Sunil Varghese", "Route B (City Center)", 10.3600, 76.2900, "ON TRIP", "18 Mins Saved (ML Dynamic)", "PAID"));
        busRepository.put("BUS03", new Bus("BUS03", "KL-08-AX-3003", "Santhosh M.", "Route C (Suburbs)", 10.3400, 76.2700, "INACTIVE", "Not Started", "PENDING"));
    }

    public List<Bus> getAllBuses() {
        return new ArrayList<>(busRepository.values());
    }

    public Bus getBusById(String busId) {
        return busRepository.get(busId);
    }

    public List<Route> getAllRoutes() {
        return new ArrayList<>(routeRepository.values());
    }

    public boolean updateLocation(String busId, double lat, double lng) {
        Bus bus = busRepository.get(busId);
        if (bus != null) {
            bus.setCurrentLat(lat);
            bus.setCurrentLng(lng);
            bus.setStatus("ON TRIP");
            bus.setEstimatedTimeArrival("Live Calculated (Approaching Stop)");
            return true;
        }
        return false;
    }

    public boolean updateTripStatus(String busId, String status) {
        Bus bus = busRepository.get(busId);
        if (bus != null) {
            bus.setStatus(status);
            return true;
        }
        return false;
    }

    public void addBus(Bus bus) {
        busRepository.put(bus.getBusId(), bus);
    }
}