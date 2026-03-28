package com.iris.clinic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Embeddable
public class Address {
    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String street;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String city;

    @NotBlank
    @Size(max = 20)
    @Column(name = "zipcode", nullable = false, length = 20)
    private String zipCode;
}
