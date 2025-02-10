package com.azathoth.spring_security_learning.controller;

import com.azathoth.spring_security_learning.model.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final Patient patient;

    public PatientController(Patient patient) {
        this.patient = patient;
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
}
