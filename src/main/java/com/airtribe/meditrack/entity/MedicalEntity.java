package com.airtribe.meditrack.entity;

public abstract class MedicalEntity {

    private final Long id;

    protected MedicalEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public abstract void displayDetails();
}