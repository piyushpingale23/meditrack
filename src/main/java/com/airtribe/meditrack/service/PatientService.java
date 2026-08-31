package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.PatientNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class PatientService {

    private final List<Patient> patients = new ArrayList<>();

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public Patient getPatientById(Long patientId) {
        return patients.stream()
                .filter(patient -> patient.getId().equals(patientId))
                .findFirst()
                .orElseThrow(() ->
                        new PatientNotFoundException(
                                "Patient not found with id: " + patientId
                        )
                );
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients);
    }
}