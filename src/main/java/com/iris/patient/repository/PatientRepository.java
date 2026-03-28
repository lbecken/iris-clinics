package com.iris.patient.repository;

import com.iris.patient.model.Patient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class PatientRepository {

    @PersistenceContext
    private EntityManager em;

    public Patient findById(Long id) {
        return em.find(Patient.class, id);
    }

    public Patient findByPatientId(Long patientId) {
        return em.find(Patient.class, patientId);
    }

    public List<Patient> findAll() {
        return em.createQuery("""
            SELECT p
            FROM Patient p
            ORDER BY p.lastName, p.firstName
        """, Patient.class).getResultList();
    }

    public List<Patient> findByBirthDate(LocalDate birthDate) {
        return em.createQuery("""
            SELECT p
            FROM Patient p
            WHERE p.birthDate = :birthDate
            ORDER BY p.lastName, p.firstName
        """, Patient.class)
                .setParameter("birthDate", birthDate)
                .getResultList();
    }

    public void persist(Patient patient) {
        em.persist(patient);
    }

    public Patient merge(Patient patient) {
        return em.merge(patient);
    }

    public void remove(Patient patient) {
        em.remove(patient);
    }

}
