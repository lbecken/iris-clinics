package com.iris.staff.rest;

import com.iris.staff.model.Doctor;
import com.iris.staff.service.DoctorService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/doctors")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class DoctorResource {
    @Inject
    private DoctorService doctorService;

    @GET
    public List<Doctor> getAll() {
        return doctorService.getDoctors();
    }
}