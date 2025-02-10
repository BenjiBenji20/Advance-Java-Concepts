package com.azathoth.spring_security_learning.repository;

import com.azathoth.spring_security_learning.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findUserByUsername(String username);
}
