package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.BillSummary;

import java.util.ArrayList;
import java.util.List;

public class BillingService {

    private final List<Bill> bills = new ArrayList<>();

    public Bill generateBill(Long billId,
                             Appointment appointment,
                             double consultationFee) {

        Bill bill = new Bill(
                billId,
                appointment.getPatient(),
                consultationFee
        );

        bill.calculateAmount();

        bills.add(bill);

        return bill;
    }

    public Bill getBillById(Long billId) {

        return bills.stream()
                .filter(bill -> bill.getId().equals(billId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Bill not found with id: " + billId
                        )
                );
    }

    public List<Bill> getAllBills() {
        return new ArrayList<>(bills);
    }

    public BillSummary getBillSummary(Long billId) {

        Bill bill = getBillById(billId);

        return bill.getSummary();
    }
}