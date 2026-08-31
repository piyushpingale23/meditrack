package com.airtribe.meditrack.entity;

public final class BillSummary {

    private final Long billId;
    private final String patientName;
    private final double consultationFee;
    private final double tax;
    private final double totalAmount;

    public BillSummary(Long billId,
                       String patientName,
                       double consultationFee,
                       double tax,
                       double totalAmount) {

        this.billId = billId;
        this.patientName = patientName;
        this.consultationFee = consultationFee;
        this.tax = tax;
        this.totalAmount = totalAmount;
    }

    public Long getBillId() {
        return billId;
    }

    public String getPatientName() {
        return patientName;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public double getTax() {
        return tax;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "BillSummary{" +
                "billId=" + billId +
                ", patientName='" + patientName + '\'' +
                ", consultationFee=" + consultationFee +
                ", tax=" + tax +
                ", totalAmount=" + totalAmount +
                '}';
    }
}