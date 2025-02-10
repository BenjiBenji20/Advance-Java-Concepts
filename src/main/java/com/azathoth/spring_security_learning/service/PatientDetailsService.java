package com.azathoth.spring_security_learning.service;

import com.azathoth.spring_security_learning.model.Patient;
import com.azathoth.spring_security_learning.model.PatientPrincipal;
import com.azathoth.spring_security_learning.repository.PatientRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PatientDetailsService implements UserDetailsService {
    private final PatientRepository patientRepository;

    public PatientDetailsService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Patient patient = patientRepository.findUserByUsername(username);

        // if patient is not found
        if(patient == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new PatientPrincipal(patient);
    }
}
