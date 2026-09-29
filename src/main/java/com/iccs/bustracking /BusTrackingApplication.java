package com.iccs.bustracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BusTrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusTrackingApplication.class, args);
        System.out.println("====================================================");
        System.out.println(" ICCS College Bus Tracking System Started ");
        System.out.println(" Access Portals at: ");
        System.out.println(" Student View : http://localhost:8080/student");
        System.out.println(" Driver Panel : http://localhost:8080/driver");
        System.out.println(" Admin Control: http://localhost:8080/admin");
        System.out.println("====================================================");
    }
}