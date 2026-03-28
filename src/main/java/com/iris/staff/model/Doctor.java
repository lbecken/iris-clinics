package com.iris.staff.model;

import com.iris.clinic.model.Clinic;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column()
    private Boolean active;

    @Convert(converter = SpecialtyConverter.class)
    private Specialty specialty;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "clinic_doctor",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id")
    )
    private List<Clinic> clinics = new ArrayList<>();

    // Helper methods

    public void addClinic(Clinic clinic) {
        clinics.add(clinic);
        clinic.getDoctors().add(this);
    }

    public void removeClinic(Clinic clinic) {
        clinics.remove(clinic);
        clinic.getDoctors().remove(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
