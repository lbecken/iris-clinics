package com.iris.patient;

import com.iris.patient.model.Patient;
import com.iris.patient.service.PatientService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class PatientEditBean implements Serializable {

    @Inject
    private PatientService patientService;

    private Patient patient;

    @PostConstruct
    public void init() {
        String idParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");

        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            patient = patientService.getPatientById(id);
        }
    }

    public String save() {
        patientService.save(patient);
        return "patients-list.xhtml?faces-redirect=true";
    }

    public Patient getPatient() {
        return patient;
    }
}