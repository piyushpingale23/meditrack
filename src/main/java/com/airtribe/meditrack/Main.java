package com.airtribe.meditrack;

import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.enums.Specialization;

public class Main {

    public static void main(String[] args) {

        System.out.println("Available Specializations");

        for (Specialization specialization : Specialization.values()) {
            System.out.println(specialization);
        }

        System.out.println();

        AppointmentStatus status = AppointmentStatus.PENDING;

        System.out.println("Current Status : " + status);
    }
}