package com.iris.staff.service;

import com.iris.patient.model.Patient;
import com.iris.staff.model.Doctor;
import com.iris.staff.repository.DoctorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DoctorService {
    @Inject
    private DoctorRepository doctorRepository;

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    @Transactional
    public void setDoctorActive(Long id, Boolean isActive) {
        Doctor doctor = doctorRepository.findById(id);
        if (doctor != null) {
            doctor.setActive(isActive);
            save(doctor);
        }
    }

    @Transactional
    public void save(Doctor doctor) {
        if (doctor.getId() == null) {
            doctorRepository.persist(doctor); // new entity → INSERT
        } else {
            doctorRepository.merge(doctor); // existing entity → UPDATE
        }
    }
}
