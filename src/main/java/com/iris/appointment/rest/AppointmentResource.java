package com.iris.appointment.rest;

import com.iris.appointment.model.Appointment;
import com.iris.appointment.service.AppointmentService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/appointments")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class AppointmentResource {
    @Inject
    private AppointmentService appointmentService;

    @GET
    public List<Appointment> getAll() {
        return appointmentService.getAppointments();
    }
}