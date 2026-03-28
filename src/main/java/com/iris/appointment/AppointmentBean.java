package com.iris.appointment;

import com.iris.appointment.model.Appointment;
import com.iris.appointment.service.AppointmentService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AppointmentBean implements Serializable {

    @Inject
    private AppointmentService appointmentService;

    private List<Appointment> appointments;

    @PostConstruct
    public void loadAppointments() {
        appointments = appointmentService.getAppointments();
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
