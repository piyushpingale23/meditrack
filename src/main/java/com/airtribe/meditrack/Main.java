package com.airtribe.meditrack;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.BillingService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final DoctorService doctorService = new DoctorService();
    private static final PatientService patientService = new PatientService();
    private static final AppointmentService appointmentService = new AppointmentService(doctorService, patientService);
    private static final BillingService billingService = new BillingService();

    public static void main(String[] args) {

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            try {

                switch (choice) {

                    case 1 -> addDoctor();

                    case 2 -> addPatient();

                    case 3 -> listDoctors();

                    case 4 -> listPatients();

                    case 5 -> bookAppointment();

                    case 6 -> listAppointments();

                    case 7 -> confirmAppointment();

                    case 8 -> cancelAppointment();

                    case 9 -> generateBill();

                    case 10 -> viewBill();

                    case 11 -> payBill();

                    case 12 -> printBillReceipt();

                    case 0 -> {
                        running = false;
                        System.out.println("Exiting MediTrack...");
                    }

                    default -> System.out.println("Invalid choice.");

                }

            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println();
        System.out.println("========== MediTrack ==========");
        System.out.println("1.  Add Doctor");
        System.out.println("2.  Add Patient");
        System.out.println("3.  List Doctors");
        System.out.println("4.  List Patients");
        System.out.println("5.  Book Appointment");
        System.out.println("6.  List Appointments");
        System.out.println("7.  Confirm Appointment");
        System.out.println("8.  Cancel Appointment");
        System.out.println("9.  Generate Bill");
        System.out.println("10. View Bill");
        System.out.println("11. Pay Bill");
        System.out.println("12. Print Bill Receipt");
        System.out.println("0.  Exit");
        System.out.println("===============================");
    }

    private static void addDoctor() {

        Long id = readLong("Enter doctor ID: ");
        String name = readString("Enter doctor name: ");
        int age = readInt("Enter doctor age: ");
        String mobile = readString("Enter doctor mobile: ");
        String specializationInput = readString("Enter specialization: ");
        Specialization specialization = Specialization.valueOf(specializationInput.toUpperCase());
        double consultationFee = readDouble("Enter consultation fee: ");

        Doctor doctor = new Doctor(id, name, age, mobile, specialization, consultationFee);

        doctorService.addDoctor(doctor);

        System.out.println("Doctor added successfully.");
    }

    private static void addPatient() {

        Long id = readLong("Enter patient ID: ");
        String name = readString("Enter patient name: ");
        int age = readInt("Enter patient age: ");
        String mobile = readString("Enter patient mobile: ");
        String symptoms = readString("Enter symptoms: ");

        Patient patient = new Patient(id, name, age, mobile, symptoms);

        patientService.addPatient(patient);

        System.out.println("Patient added successfully.");
    }

    private static void listDoctors() {

        List<Doctor> doctors = doctorService.getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }

        System.out.println("\n----- Doctors -----");

        for (Doctor doctor : doctors) {
            doctor.displayDetails();
            System.out.println();
        }
    }

    private static void listPatients() {

        List<Patient> patients = patientService.getAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        System.out.println("\n----- Patients -----");

        for (Patient patient : patients) {
            patient.displayDetails();
            System.out.println();
        }
    }

    private static void bookAppointment() {

        Long appointmentId = readLong("Enter appointment ID: ");
        Long doctorId = readLong("Enter doctor ID: ");
        Long patientId = readLong("Enter patient ID: ");
        String dateTime = readString("Enter appointment date-time " + "(yyyy-MM-dd HH:mm): ");
        LocalDateTime appointmentDateTime = LocalDateTime.parse(dateTime.replace(" ", "T"));

        Appointment appointment = appointmentService.bookAppointment(appointmentId, doctorId, patientId, appointmentDateTime);

        System.out.println("Appointment booked successfully.");

        appointment.displayDetails();
    }

    private static void listAppointments() {

        List<Appointment> appointments = appointmentService.getAllAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        System.out.println("\n----- Appointments -----");

        for (Appointment appointment : appointments) {
            appointment.displayDetails();
            System.out.println();
        }
    }

    private static void confirmAppointment() {
        Long appointmentId = readLong("Enter appointment ID: ");
        appointmentService.confirmAppointment(appointmentId);
        System.out.println("Appointment confirmed successfully.");
    }

    private static void cancelAppointment() {
        Long appointmentId = readLong("Enter appointment ID: ");
        appointmentService.cancelAppointment(appointmentId);
        System.out.println("Appointment cancelled successfully.");
    }

    private static void generateBill() {
        Long billId = readLong("Enter bill ID: ");
        Long appointmentId = readLong("Enter appointment ID: ");
        double consultationFee = readDouble("Enter consultation fee: ");
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        Bill bill = billingService.generateBill(billId, appointment, consultationFee);

        System.out.println("Bill generated successfully.");
        System.out.println("Total Amount: " + bill.calculateAmount());
    }

    private static void viewBill() {
        Long billId = readLong("Enter bill ID: ");

        Bill bill = billingService.getBillById(billId);
        BillSummary summary = bill.getSummary();

        System.out.println("----- Bill Summary -----");
        System.out.println("Bill ID          : " + summary.getBillId());
        System.out.println("Patient          : " + summary.getPatientName());
        System.out.println("Consultation Fee : " + summary.getConsultationFee());
        System.out.println("Tax              : " + summary.getTax());
        System.out.println("Total Amount     : " + summary.getTotalAmount());
    }

    private static void payBill() {
        Long billId = readLong("Enter bill ID: ");
        Bill bill = billingService.getBillById(billId);
        bill.pay();
    }

    private static void printBillReceipt() {
        Long billId = readLong("Enter bill ID: ");
        Bill bill = billingService.getBillById(billId);
        bill.printReceipt();
    }

    private static int readInt(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }

    private static Long readLong(String message) {
        System.out.print(message);
        return Long.parseLong(scanner.nextLine());
    }

    private static double readDouble(String message) {
        System.out.print(message);
        return Double.parseDouble(scanner.nextLine());
    }

    private static String readString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}