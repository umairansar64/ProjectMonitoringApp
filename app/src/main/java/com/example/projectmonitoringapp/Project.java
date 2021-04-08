package com.example.projectmonitoringapp;

public class Project {

    private String title;
    private String id;
    private String tutor;
    private String client;
    private String unit;

    public Project(String title, String id, String tutor, String client, String unit) {

        this.title = title;
        this.id = id;
        this.tutor = tutor;
        this.client = client;
        this.unit = unit;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String toString() {
        return (title + " - " + id);
    }
}
