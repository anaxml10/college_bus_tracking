package com.iccs.bustracking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LocationUpdate {

    @Id
    private String busId;
    private String driverName;
    private double latitude;
    private double longitude;
    private String status; // ACTIVE, INACTIVE, BREAKDOWN
    private String password;
    private double speed;

    public LocationUpdate() {
        this.status = "INACTIVE";
        this.driverName = "Driver";
        this.password = "1234";
        this.speed = 0.0;
    }

    public LocationUpdate(String busId, double latitude, double longitude, String status) {
        this.busId = busId;
        this.driverName = "Driver";
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.password = "1234";
        this.speed = 0.0;
    }

    public LocationUpdate(String busId, double latitude, double longitude, String status, String password) {
        this.busId = busId;
        this.driverName = "Driver";
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.password = password;
        this.speed = 0.0;
    }

    public LocationUpdate(String busId, String driverName, double latitude, double longitude, String status, String password) {
        this.busId = busId;
        this.driverName = driverName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.password = password;
        this.speed = 0.0;
    }

    public String getBusId() { return busId; }
    public void setBusId(String busId) { this.busId = busId; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }
}