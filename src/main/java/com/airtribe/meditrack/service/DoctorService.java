package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.exception.DoctorNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {

    private final List<Doctor> doctors = new ArrayList<>();

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public Doctor getDoctorById(Long doctorId) {
        return doctors.stream()
                .filter(doctor -> doctor.getId().equals(doctorId))
                .findFirst()
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor not found with id: " + doctorId
                        )
                );
    }

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors);
    }
}