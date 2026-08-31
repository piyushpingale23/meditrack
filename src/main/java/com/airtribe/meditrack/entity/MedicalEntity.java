package com.airtribe.meditrack.entity;

public abstract class MedicalEntity {

    private Long id;

    public MedicalEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract void displayDetails();
}