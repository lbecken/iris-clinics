package com.iris.patient;

import com.iris.patient.model.Patient;
import com.iris.patient.service.PatientService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
// => serializable because container (CDI) may need to save and restore state (view, session scopes)
public class PatientBean implements Serializable {

    @Inject
    private PatientService patientService;

    private List<Patient> patients;

    @PostConstruct
    public void loadPatients() {
        patients = patientService.getPatients();
    }

    public List<Patient> getPatients() {
        return patients;
    }
}
