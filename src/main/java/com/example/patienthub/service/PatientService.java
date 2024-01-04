package com.example.patienthub.service;

import com.example.patienthub.exception.PatientNotFoundException;
import com.example.patienthub.model.Patient;
import com.example.patienthub.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Cacheable("patients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Cacheable(value = "patients", key = "#id")
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));
    }

    @CacheEvict(value = "patients", allEntries = true)
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @CacheEvict(value = "patients", key = "#id")
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }
}