package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    private final List<Appointment> appointments = new ArrayList<>();

    private final DoctorService doctorService;
    private final PatientService patientService;

    public AppointmentService(DoctorService doctorService, PatientService patientService) {

        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    public Appointment bookAppointment(Long appointmentId, Long doctorId, Long patientId, LocalDateTime appointmentDateTime) {

        Doctor doctor = doctorService.getDoctorById(doctorId);

        Patient patient = patientService.getPatientById(patientId);

        Appointment appointment = new Appointment(
                appointmentId,
                patient,
                doctor,
                appointmentDateTime
        );

        appointments.add(appointment);

        return appointment;
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    public Appointment getAppointmentById(Long appointmentId) {

        return appointments.stream()
                .filter(appointment ->
                        appointment.getId().equals(appointmentId))
                .findFirst()
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment not found with id: "
                                        + appointmentId
                        )
                );
    }

    public void confirmAppointment(Long appointmentId) {

        Appointment appointment =
                getAppointmentById(appointmentId);

        appointment.confirm();
    }

    public void cancelAppointment(Long appointmentId) {

        Appointment appointment =
                getAppointmentById(appointmentId);

        appointment.cancel();
    }
}