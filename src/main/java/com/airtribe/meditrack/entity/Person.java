package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.exception.InvalidDataException;

public class Person extends MedicalEntity {

    private String name;
    private int age;
    private String mobile;

    public Person(Long id, String name, int age, String mobile) {
        super(id);

        setName(name);
        setAge(age);
        setMobile(mobile);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Name cannot be empty");
        }

        this.name = name.trim();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {

        if (age <= 0 || age > 120) {
            throw new InvalidDataException("Invalid age : " + age);
        }

        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {

        if (mobile == null || mobile.trim().isEmpty()) {
            throw new InvalidDataException("Mobile number cannot be empty");
        }

        this.mobile = mobile.trim();
    }

    @Override
    public void displayDetails() {
        System.out.println("ID     : " + getId());
        System.out.println("Name   : " + name);
        System.out.println("Age    : " + age);
        System.out.println("Mobile : " + mobile);
    }
}