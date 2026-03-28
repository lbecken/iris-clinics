package com.iris.staff.repository;

import com.iris.staff.model.Doctor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class DoctorRepository {

    @PersistenceContext
    private EntityManager em;

    public Doctor findById(Long id) {
        return em.find(Doctor.class, id);
    }

    public List<Doctor> findAll() {
        return em.createQuery("""
            SELECT d
            FROM Doctor d
            ORDER BY d.lastName, d.firstName
        """).getResultList();
    }

    public void persist(Doctor doctor) {
        em.persist(doctor);
    }

    public Doctor merge(Doctor doctor) {
        return em.merge(doctor);
    }

    public void remove(Doctor doctor) {
        em.remove(doctor);
    }
}
