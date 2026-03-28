package com.iris.clinic.model;

import com.iris.staff.model.Doctor;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clinic")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    //@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "address_id", nullable = false)
    //private AddressEntity address;
    @Embedded
    @Valid
    private Address address;

    @Column(length = 20)
    private String phone;

    @ManyToMany(mappedBy = "clinics", fetch = FetchType.LAZY)
    private List<Doctor> doctors = new ArrayList<>();

    public List<Doctor> getDoctors() {
        return doctors;
    }
}
