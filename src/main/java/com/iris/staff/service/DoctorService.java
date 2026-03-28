package com.iris.staff.service;

import com.iris.staff.model.Doctor;
import com.iris.staff.repository.DoctorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DoctorService {
    @Inject
    private DoctorRepository doctorRepository;

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }
}
