package com.iccs.bustracking.service;

import com.iccs.bustracking.model.Bus;
import com.iccs.bustracking.controller.AdminController;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TrackingService {

    public Collection<Bus> getAllBuses() {
        return AdminController.busStore.values();
    }

    public Bus getBusById(String busId) {
        return AdminController.busStore.get(busId);
    }

    public void updateBusLocation(String busId, double lat, double lng, double speed) {
        Bus bus = AdminController.busStore.get(busId);
        if (bus != null) {
            bus.setLatitude(lat);
            bus.setLongitude(lng);
            bus.setSpeed(speed);
            bus.setStatus("ACTIVE");
        }
    }
}