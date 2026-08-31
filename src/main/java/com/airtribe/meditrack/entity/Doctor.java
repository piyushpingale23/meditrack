package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.Specialization;

public class Doctor extends Person {

    private Specialization specialization;
    private double consultationFee;

    public Doctor(Long id,
                  String name,
                  int age,
                  String mobile,
                  Specialization specialization,
                  double consultationFee) {

        super(id, name, age, mobile);

        setSpecialization(specialization);
        setConsultationFee(consultationFee);
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {

        if (specialization == null) {
            throw new IllegalArgumentException(
                    "Specialization cannot be null");
        }

        this.specialization = specialization;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {

        if (consultationFee < 0) {
            throw new IllegalArgumentException(
                    "Consultation fee cannot be negative");
        }

        this.consultationFee = consultationFee;
    }

    @Override
    public void displayDetails() {

        System.out.println("----- Doctor Details -----");

        System.out.println("ID               : " + getId());
        System.out.println("Name             : " + getName());
        System.out.println("Age              : " + getAge());
        System.out.println("Mobile           : " + getMobile());
        System.out.println("Specialization   : " + specialization);
        System.out.println("Consultation Fee : " + consultationFee);
    }
}