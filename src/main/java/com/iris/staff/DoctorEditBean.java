package com.iris.staff;

import com.iris.patient.model.Patient;
import com.iris.patient.service.PatientService;
import com.iris.staff.model.Doctor;
import com.iris.staff.service.DoctorService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class DoctorEditBean implements Serializable {
    @Inject
    private DoctorService doctorService;

    private Doctor doctor;

    // views
    private static final String DOCTORS_LIST = "/staff/doctors-list?faces-redirect=true";

    @PostConstruct
    public void init() {
        String idParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");

        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            doctor = doctorService.getDoctorById(id);
        }
    }

    public String save() {
        doctorService.save(doctor);
        return DOCTORS_LIST;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
