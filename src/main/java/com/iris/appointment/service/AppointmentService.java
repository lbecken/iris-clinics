package com.iris.appointment.service;

import com.iris.appointment.model.Appointment;
import com.iris.appointment.repository.AppointmentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class AppointmentService {
    @Inject
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }
}
