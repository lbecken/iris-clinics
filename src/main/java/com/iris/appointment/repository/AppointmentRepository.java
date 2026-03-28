package com.iris.appointment.repository;

import com.iris.appointment.model.Appointment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class AppointmentRepository {

    @PersistenceContext
    private EntityManager em;

    public Appointment findById(Long id) {
        return em.find(Appointment.class, id);
    }

    public List<Appointment> findAll() {
        return em.createQuery("""
            SELECT a
            FROM Appointment a
            ORDER BY a.appointmentDate DESC
        """).getResultList();
    }

    public void persist(Appointment appointment) {
        em.persist(appointment);
    }

    public Appointment merge(Appointment appointment) {
        return em.merge(appointment);
    }

    public void remove(Appointment appointment) {
        em.remove(appointment);
    }
}
