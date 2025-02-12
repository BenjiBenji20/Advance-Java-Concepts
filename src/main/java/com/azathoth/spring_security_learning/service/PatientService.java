package com.azathoth.spring_security_learning.service;

import com.azathoth.spring_security_learning.model.Patient;
import com.azathoth.spring_security_learning.repository.PatientRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Patient register(Patient patient) {
        patient.setPassword(encoder.encode(patient.getPassword()));

        return patientRepository.save(patient);
    }

}
