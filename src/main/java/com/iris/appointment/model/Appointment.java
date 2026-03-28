package com.iris.appointment.model;

import com.iris.patient.model.Patient;
import com.iris.staff.model.Doctor;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime; // modern java.time API
import java.time.LocalTime;

@Entity
@Table(name="appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime appointmentDate; // PostgreSQL: TIMESTAMP WITHOUT TIME ZONE
    //private LocalDate appointmentDate; // To managed bean
    //private LocalTime appointmentTime; // To managed bean

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Convert(converter = AppointmentStatusConverter.class)
    private AppointmentStatus status;

    @Convert(converter = AppointmentReasonConverter.class)
    private AppointmentReason reason;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }
}
