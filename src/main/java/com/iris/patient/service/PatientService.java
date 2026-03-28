package com.iris.patient.service;

import com.iris.patient.model.Patient;
import com.iris.patient.repository.PatientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PatientService {
    @Inject
    private PatientRepository patientRepository;

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @Transactional
    public void save(Patient patient) {
        if (patient.getId() == null) {
            patientRepository.persist(patient); // new entity → INSERT
        } else {
            patientRepository.merge(patient); // existing entity → UPDATE
        }
    }
}
