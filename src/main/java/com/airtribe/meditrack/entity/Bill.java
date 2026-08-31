package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.interfaces.Payable;

public class Bill implements Payable {

    private Long id;
    private Patient patient;
    private double consultationFee;
    private double tax;

    private boolean paid;

    public Bill(Long id,
                Patient patient,
                double consultationFee) {

        this.id = id;
        this.patient = patient;
        this.consultationFee = consultationFee;
    }

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public double getTax() {
        return tax;
    }

    public boolean isPaid() {
        return paid;
    }

    public BillSummary getSummary() {

        double total = calculateAmount();

        return new BillSummary(
                id,
                patient.getName(),
                consultationFee,
                tax,
                total
        );
    }

    @Override
    public double calculateAmount() {

        tax = consultationFee
                * Constants.TAX_RATE
                / 100;

        return consultationFee + tax;
    }

    @Override
    public void pay() {

        if (paid) {
            System.out.println("Bill is already paid.");
            return;
        }

        double total = calculateAmount();

        paid = true;

        System.out.println(
                "Payment successful. Amount paid : " + total
        );
    }

    @Override
    public void printReceipt() {

        System.out.println("----- Payment Receipt -----");
        System.out.println("Bill ID          : " + id);
        System.out.println("Patient          : " + patient.getName());
        System.out.println("Consultation Fee : " + consultationFee);
        System.out.println("Tax              : " + tax);
        System.out.println("Total            : " + calculateAmount());
        System.out.println("Paid             : " + paid);
    }
}