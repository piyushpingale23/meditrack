package com.airtribe.meditrack.interfaces;

public interface Payable {

    double calculateAmount();

    void pay();

    default void printReceipt() {
        System.out.println("Payment receipt generated.");
    }
}