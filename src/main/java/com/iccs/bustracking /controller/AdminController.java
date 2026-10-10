
package com.iccs.bustracking.controller;

import com.iccs.bustracking.model.Bus;
import com.iccs.bustracking.model.Student;
import com.iccs.bustracking.repository.BusRepository;
import com.iccs.bustracking.repository.StudentRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class AdminController implements CommandLineRunner {

    public static final Map<String, Bus> busStore =
            new ConcurrentHashMap<>();

    public static final Map<String, List<String>> busStopsMap =
            new ConcurrentHashMap<>();

    public static final Map<String, String> studentStore =
            new ConcurrentHashMap<>();

    private final BusRepository busRepository;
    private final StudentRepository studentRepository;

    public AdminController(BusRepository busRepository,
                           StudentRepository studentRepository) {
        this.busRepository = busRepository;
        this.studentRepository = studentRepository;
    }

    // Load saved data from H2 when application starts
    @Override
    public void run(String... args) {
        for (Bus bus : busRepository.findAll()) {
            busStore.put(bus.getBusId(), bus);
        }

        for (Student student : studentRepository.findAll()) {
            studentStore.put(
                    student.getUsername(),
                    student.getPassword()
            );
        }

        // Add default students only if they do not already exist
        addDefaultStudent("anax", "1234");
        addDefaultStudent("student01", "1234");
    }

    private void addDefaultStudent(String username, String password) {
        if (!studentRepository.existsById(username)) {
            Student student = new Student(username, password);
            studentRepository.save(student);
        }

        studentStore.putIfAbsent(username, password);
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("buses",
                new ArrayList<>(busStore.values()));
        model.addAttribute("students", studentStore);
        return "admin";
    }

    @PostMapping("/admin/addBus")
    @ResponseBody
    public ResponseEntity<?> addBus(
            @RequestParam String busId,
            @RequestParam String driverName,
            @RequestParam String password,
            @RequestParam String regNumber,
            @RequestParam String route,
            @RequestParam(value = "stops", required = false)
            List<String> stops) {

        try {
            String cleanId = busId.trim().toUpperCase();

            Bus bus = new Bus(
                    cleanId,
                    driverName.trim(),
                    password.trim(),
                    regNumber.trim().toUpperCase(),
                    route.trim()
            );

            bus.setStatus("INACTIVE");

            // Save permanently in H2
            Bus savedBus = busRepository.save(bus);
            busStore.put(cleanId, savedBus);

            if (stops != null) {
                busStopsMap.put(cleanId, new ArrayList<>(stops));
            } else {
                busStopsMap.put(cleanId, new ArrayList<>());
            }

            return ResponseEntity.ok()
                    .body("{\"status\":\"SUCCESS\"}");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("{\"status\":\"ERROR\"}");
        }
    }

    @PostMapping("/admin/deleteBus")
    @ResponseBody
    public ResponseEntity<?> deleteBus(
            @RequestParam String busId) {

        String cleanId = busId.trim().toUpperCase();

        busRepository.deleteById(cleanId);
        busStore.remove(cleanId);
        busStopsMap.remove(cleanId);

        return ResponseEntity.ok()
                .body("{\"status\":\"SUCCESS\"}");
    }

    @PostMapping("/admin/addStudent")
    @ResponseBody
    public ResponseEntity<?> addStudent(
            @RequestParam String username,
            @RequestParam String password) {

        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("{\"status\":\"ERROR\"}");
        }

        try {
            String cleanUser = username.trim().toLowerCase();
            String cleanPassword = password.trim();

            Student student = new Student(cleanUser, cleanPassword);

            // Save permanently in H2
            studentRepository.save(student);
            studentStore.put(cleanUser, cleanPassword);

            return ResponseEntity.ok()
                    .body("{\"status\":\"SUCCESS\"}");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("{\"status\":\"ERROR\"}");
        }
    }

    @PostMapping("/api/student/login")
    @ResponseBody
    public ResponseEntity<?> studentLogin(
            @RequestParam String username,
            @RequestParam String password) {

        String cleanUser = username.trim().toLowerCase();

        Student student = studentRepository
                .findById(cleanUser)
                .orElse(null);

        if (student != null
                && student.getPassword().equals(password.trim())) {
            return ResponseEntity.ok()
                    .body("{\"status\":\"SUCCESS\"}");
        }

        return ResponseEntity.status(401)
                .body("{\"status\":\"INVALID\"}");
    }

    @PostMapping("/api/bus/updateAlert")
    @ResponseBody
    public ResponseEntity<?> updateAlert(
            @RequestParam String busId,
            @RequestParam String status) {

        String cleanId = busId.trim().toUpperCase();
        Bus bus = busStore.get(cleanId);

        if (bus != null) {
            bus.setStatus(status.trim().toUpperCase());

            // Save updated status in H2
            busRepository.save(bus);

            return ResponseEntity.ok()
                    .body("{\"status\":\"SUCCESS\"}");
        }

        return ResponseEntity.badRequest()
                .body("{\"status\":\"ERROR\"}");
    }
}
