package com.iccs.bustracking.model;

public class Bus {
    private String busId;
    private String busNumber;
    private String driverName;
    private String routeName;
    private double currentLat;
    private double currentLng;
    private String status;
    private String estimatedTimeArrival;
    private String feeStatus;

    public Bus() {
    }

    public Bus(String busId, String busNumber, String driverName, String routeName, double currentLat, double currentLng, String status, String estimatedTimeArrival, String feeStatus) {
        this.busId = busId;
        this.busNumber = busNumber;
        this.driverName = driverName;
        this.routeName = routeName;
        this.currentLat = currentLat;
        this.currentLng = currentLng;
        this.status = status;
        this.estimatedTimeArrival = estimatedTimeArrival;
        this.feeStatus = feeStatus;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public double getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(double currentLat) {
        this.currentLat = currentLat;
    }

    public double getCurrentLng() {
        return currentLng;
    }

    public void setCurrentLng(double currentLng) {
        this.currentLng = currentLng;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstimatedTimeArrival() {
        return estimatedTimeArrival;
    }

    public void setEstimatedTimeArrival(String estimatedTimeArrival) {
        this.estimatedTimeArrival = estimatedTimeArrival;
    }

    public String getFeeStatus() {
        return feeStatus;
    }

    public void setFeeStatus(String feeStatus) {
        this.feeStatus = feeStatus;
    }
}