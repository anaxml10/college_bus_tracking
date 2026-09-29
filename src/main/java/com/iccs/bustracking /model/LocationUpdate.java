package com.iccs.bustracking.model;

public class LocationUpdate {
    private String busId;
    private double latitude;
    private double longitude;

    public LocationUpdate() {
    }

    public LocationUpdate(String busId, double latitude, double longitude) {
        this.busId = busId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}