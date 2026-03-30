package com.iris.staff.service;

import com.iris.patient.model.Gender;
import com.iris.patient.model.GenderConverter;
import com.iris.patient.model.Patient;
import com.iris.patient.repository.PatientRepository;
import com.iris.patient.service.PatientService;
import jakarta.inject.Inject;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.jboss.arquillian.container.test.api.Deployment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ArquillianExtension.class)
public class PatientServiceIT {
    @Inject
    private PatientService patientService;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                // Add the classes under test
                .addClass(PatientService.class)
                .addClass(PatientRepository.class)
                .addClass(Patient.class)
                .addClass(Gender.class)
                .addClass(GenderConverter.class)
                // beans.xml enables CDI
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // persistence.xml for JPA
                .addAsResource("META-INF/persistence.xml");
    }

    @Test
    void shouldFindAllPatients() {
        var patients = patientService.getPatients();
        assertNotNull(patients);
        assertFalse(patients.isEmpty());
    }

    @Test
    void shouldFindPatientById() {
        Patient patient = patientService.getPatientById(1L);
        assertNotNull(patient);
        assertNotNull(patient.getLastName());
    }
}
