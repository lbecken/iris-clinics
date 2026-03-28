package com.iris.patient.model;

import jakarta.persistence.*;

import java.time.LocalDate; // modern java.time API

@Entity
@Table(name="patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="pid")
    private Long patientId;
    @Column(name="first_name", nullable=false, length=100)
    private String firstName;
    @Column(name="last_name", nullable = false, length=100)
    private String lastName;
    private String email;
    private String phone;
    //@Enumerated(EnumType.STRING)
    @Column(name="gender", nullable = false)
    @Convert(converter = GenderConverter.class)
    private Gender gender;
    @Column(name="birth_date", nullable=false)
    private LocalDate birthDate;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
