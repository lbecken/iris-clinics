package com.iris.staff.service;

import com.iris.staff.model.Doctor;
import com.iris.staff.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

// enable Mockito annotations
@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {
    // create fake repository
    @Mock
    private DoctorRepository doctorRepository;

    // create real service that uses fake repository
    @InjectMocks
    private DoctorService doctorService;

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setId(1L);
        doctor.setActive(true);
    }

    @Test
    void setDoctorActive_shouldDeactivateDoctor() {
        // prepare/set mock behavior
        when(doctorRepository.findById(1L)).thenReturn(doctor);

        // This is the call to be tested...
        doctorService.setDoctorActive(1L, false);

        // test assertions

        assertFalse(doctor.getActive());
        // assert method was called on the repository mock (only once)
        verify(doctorRepository).merge(doctor);
    }

    @Test
    void setDoctorActive_shouldNotFailWhenDoctorNotFound() {
        when(doctorRepository.findById(99L)).thenReturn(null);

        doctorService.setDoctorActive(99L, false);

        verify(doctorRepository, never()).merge(any());
    }

    @Test
    void save_shouldPersistNewDoctor() {
        Doctor newDoctor = new Doctor();
        // id is null → new entity

        doctorService.save(newDoctor);

        verify(doctorRepository).persist(newDoctor);
        verify(doctorRepository, never()).merge(any());
    }

    @Test
    void save_shouldMergeExistingDoctor() {
        doctorService.save(doctor); // id = 1L → existing

        verify(doctorRepository).merge(doctor);
        verify(doctorRepository, never()).persist(any());
    }
}
