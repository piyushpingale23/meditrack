package com.airtribe.meditrack;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.BillSummary;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.Specialization;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        Doctor doctor = new Doctor(
                1L,
                "Dr. Amit Sharma",
                42,
                "9876543210",
                Specialization.CARDIOLOGIST,
                1500
        );

        Patient patient = new Patient(
                101L,
                "Rahul",
                30,
                "9123456780",
                "Chest pain"
        );

        patient.addMedicalHistory("Blood pressure checked");

        Appointment appointment = new Appointment(
                1001L,
                patient,
                doctor,
                LocalDateTime.now().plusDays(1)
        );

        appointment.displayDetails();

        System.out.println();

        appointment.confirm();

        appointment.displayDetails();

        System.out.println();

        Bill bill = new Bill(
                5001L,
                patient,
                doctor.getConsultationFee()
        );

        System.out.println(
                "Total Bill : " + bill.calculateAmount()
        );

        bill.pay();

        System.out.println();

        bill.printReceipt();

        System.out.println();

        BillSummary summary = bill.getSummary();

        System.out.println("Bill Summary:");
        System.out.println(summary);
    }
}