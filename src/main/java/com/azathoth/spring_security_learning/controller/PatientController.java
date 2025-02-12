package com.azathoth.spring_security_learning.controller;

import com.azathoth.spring_security_learning.model.Patient;
import com.azathoth.spring_security_learning.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final Patient patient;
    private final PatientService patientService;

    public PatientController(Patient patient, PatientService patientService) {
        this.patient = patient;
        this.patientService = patientService;
    }

    @GetMapping("/list")
    public List<Patient> getAllPatients() {
        List<Patient> patients = Arrays.asList(
                new Patient(1, "Vanessa", "123", 21, "USER"),
                new Patient(2, "Kate", "123", 21, "USER"),
                new Patient(3, "Benji", "123", 23, "ADMIN")
        );

        return patients;
    }

    @PostMapping("/register")
    public ResponseEntity<Patient> register(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.register(patient));
    }
}
