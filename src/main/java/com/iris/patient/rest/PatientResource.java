package com.iris.patient.rest;

import com.iris.patient.model.Patient;
import com.iris.patient.service.PatientService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/patients")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class PatientResource {
    @Inject
    private PatientService patientService;

    @GET
    public List<Patient> getAll() {
        return patientService.getPatients();
    }
}