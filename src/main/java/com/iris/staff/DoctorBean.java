package com.iris.staff;

import com.iris.staff.model.Doctor;
import com.iris.staff.service.DoctorService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class DoctorBean implements Serializable {

    @Inject
    private DoctorService doctorService;

    // Doctor model
    private List<Doctor> doctors;

    @PostConstruct
    public void loadDoctors() {
        doctors = doctorService.getDoctors();
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }
}
