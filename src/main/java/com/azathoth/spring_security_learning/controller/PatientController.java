package com.azathoth.spring_security_learning.controller;

import com.azathoth.spring_security_learning.model.PatientModel;
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
    private final PatientModel patient;

    public PatientController(PatientModel patient) {
        this.patient = patient;
    }

    @GetMapping("/list")
    public List<PatientModel> getAllPatients() {
        List<PatientModel> patients = Arrays.asList(
                new PatientModel(1, "Vanessa", 21),
                new PatientModel(2, "Kate", 21),
                new PatientModel(3, "Benji", 23)
        );

        return patients;
    }
}
