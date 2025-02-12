package com.azathoth.spring_security_learning.service;

import com.azathoth.spring_security_learning.model.Patient;
import com.azathoth.spring_security_learning.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    @Autowired
    private AuthenticationManager authManager;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Patient register(Patient patient) {
        patient.setPassword(encoder.encode(patient.getPassword()));

        return patientRepository.save(patient);
    }

    public Optional<Patient> verify(Patient patient) {

        Authentication auth =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(patient.getUsername(), patient.getPassword()));

        if(auth.isAuthenticated()) {
            Patient authenticatedPatient = patientRepository.findUserByUsername(patient.getUsername());

            return Optional.of(authenticatedPatient);
        }

        return Optional.empty();
    }
}
