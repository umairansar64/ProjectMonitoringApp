package com.example.projectmonitoringapp;

public class Group {

    private String id;
    private String projectID;
    private String projectTitle;
    private String client;
    private int duration;
    private String startDate;

    public Group(String id, String projectID, String projectTitle, String client, int duration, String startDate) {
        this.id = id;
        this.projectID = projectID;
        this.projectTitle = projectTitle;
        this.client = client;
        this.duration = duration;
        this.startDate = startDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String toString() {

        if(!projectID.isEmpty()) {
            return id + " - " + projectTitle;
        }
        else{
            return id + " - (Project not assigned)";
        }
    }
}
