package com.iris.patient;

import com.iris.patient.model.Patient;
import com.iris.patient.service.PatientService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
// => serializable because container (CDI) may need to save and restore state (view, session scopes)
public class PatientBean implements Serializable {

    @Inject
    private PatientService patientService;

    //@Inject
    //private Pbkdf2PasswordHash passwordHash;

    private List<Patient> patients;

    @PostConstruct
    public void loadPatients() {
        patients = patientService.getPatients();

        //System.out.println("admin hash: " + passwordHash.generate("admin".toCharArray()));
        //System.out.println("pass123 hash: " + passwordHash.generate("pass123".toCharArray()));
    }

    public List<Patient> getPatients() {
        return patients;
    }
}
