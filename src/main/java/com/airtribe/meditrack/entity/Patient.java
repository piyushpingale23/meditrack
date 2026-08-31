package com.airtribe.meditrack.entity;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person implements Cloneable {

    private String symptoms;
    private List<String> medicalHistory;

    public Patient(Long id,
                   String name,
                   int age,
                   String mobile,
                   String symptoms) {

        super(id, name, age, mobile);

        this.symptoms = symptoms;
        this.medicalHistory = new ArrayList<>();
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public List<String> getMedicalHistory() {
        return medicalHistory;
    }

    public void addMedicalHistory(String record) {

        if (record != null && !record.trim().isEmpty()) {
            medicalHistory.add(record.trim());
        }
    }

    @Override
    public void displayDetails() {

        System.out.println("----- Patient Details -----");

        System.out.println("ID       : " + getId());
        System.out.println("Name     : " + getName());
        System.out.println("Age      : " + getAge());
        System.out.println("Mobile   : " + getMobile());
        System.out.println("Symptoms : " + symptoms);

        System.out.println("Medical History:");

        if (medicalHistory.isEmpty()) {
            System.out.println("No medical history available.");
        } else {
            for (String record : medicalHistory) {
                System.out.println("- " + record);
            }
        }
    }

    @Override
    public Patient clone() {

        try {
            Patient copy = (Patient) super.clone();

            copy.medicalHistory =
                    new ArrayList<>(this.medicalHistory);

            return copy;

        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}