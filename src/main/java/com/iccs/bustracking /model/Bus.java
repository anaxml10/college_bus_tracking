package com.iccs.bustracking.model;

public class Bus {
    private String busId;
    private String driverName;
    private String password;
    private String regNumber;
    private String route;
    private double latitude = 0.0;  // വെറുതെ തൃശ്ശൂർ കാണിക്കാതിരിക്കാൻ 0 നൽകുന്നു
    private double longitude = 0.0;
    private double speed = 0.0;
    private String status = "INACTIVE";

    public Bus() {}

    public Bus(String busId, String driverName, String password, String regNumber, String route) {
        this.busId = busId;
        this.driverName = driverName;
        this.password = password;
        this.regNumber = regNumber;
        this.route = route;
    }

    public String getBusId() { return busId; }
    public void setBusId(String busId) { this.busId = busId; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRegNumber() { return regNumber; }
    public void setRegNumber(String regNumber) { this.regNumber = regNumber; }

    public String getRoute() { return route; }
    public void setRoute(String route) { this.route = route; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}