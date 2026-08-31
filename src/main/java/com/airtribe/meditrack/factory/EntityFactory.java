package com.airtribe.meditrack.factory;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.Specialization;

public class EntityFactory {

    public static Doctor createDoctor(Long id, String name, int age, String mobile, Specialization specialization, double consultationFee) {
        return new Doctor(id, name, age, mobile, specialization, consultationFee);
    }

    public static Patient createPatient(Long id, String name, int age, String mobile, String symptoms) {
        return new Patient(id, name, age, mobile, symptoms);
    }
}