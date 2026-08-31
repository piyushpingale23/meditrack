package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.exception.InvalidDataException;

import java.time.LocalDateTime;

public class Appointment implements Cloneable {

    private Long id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime appointmentDateTime;
    private AppointmentStatus status;

    public Appointment(Long id,
                       Patient patient,
                       Doctor doctor,
                       LocalDateTime appointmentDateTime) {

        if (id == null) {
            throw new InvalidDataException("Appointment id cannot be null");
        }

        if (patient == null) {
            throw new InvalidDataException("Patient cannot be null");
        }

        if (doctor == null) {
            throw new InvalidDataException("Doctor cannot be null");
        }

        if (appointmentDateTime == null) {
            throw new InvalidDataException(
                    "Appointment date cannot be null");
        }

        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDateTime = appointmentDateTime;
        this.status = AppointmentStatus.PENDING;
    }

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void confirm() {
        status = AppointmentStatus.CONFIRMED;
    }

    public void cancel() {
        status = AppointmentStatus.CANCELLED;
    }

    public void displayDetails() {

        System.out.println("----- Appointment Details -----");

        System.out.println("Appointment ID : " + id);
        System.out.println("Patient        : " + patient.getName());
        System.out.println("Doctor         : " + doctor.getName());
        System.out.println("Specialization : "
                + doctor.getSpecialization());
        System.out.println("Date & Time    : " + appointmentDateTime);
        System.out.println("Status         : " + status);
    }

    @Override
    public Appointment clone() {

        try {
            Appointment copy = (Appointment) super.clone();

            copy.patient = patient.clone();

            // Doctor currently does not contain mutable nested objects,
            // so a new Doctor object is enough for the current design.
            copy.doctor = new Doctor(
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getAge(),
                    doctor.getMobile(),
                    doctor.getSpecialization(),
                    doctor.getConsultationFee()
            );

            return copy;

        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}