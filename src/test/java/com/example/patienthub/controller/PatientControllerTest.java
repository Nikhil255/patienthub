package com.example.patienthub.controller;

import com.example.patienthub.model.Patient;
import com.example.patienthub.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @Test
    public void testGetAllPatients() {
        Patient mockPatient = new Patient();
        mockPatient.setId(1L);
        mockPatient.setName("Nikhil Jain");
        when(patientService.getAllPatients()).thenReturn(Collections.singletonList(mockPatient));
        List<Patient> patients = patientController.getAllPatients();
        assertEquals(1, patients.size());
        assertEquals("Nikhil Jain", patients.get(0).getName());
    }

    @Test
    public void testGetPatientById() {
        Long patientId = 1L;
        Patient mockPatient = new Patient();
        mockPatient.setId(patientId);
        mockPatient.setName("Nikhil Jain");
        when(patientService.getPatientById(patientId)).thenReturn(mockPatient);
        ResponseEntity<Patient> responseEntity = patientController.getPatientById(patientId);
        assertEquals("Nikhil Jain", responseEntity.getBody().getName());
    }

}

