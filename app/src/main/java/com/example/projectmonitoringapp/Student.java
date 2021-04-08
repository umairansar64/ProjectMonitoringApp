package com.example.projectmonitoringapp;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private String grade;
    private String groupID;
    private String projectID;
    private String role;
    private String finalNotes;

    public Student(int id, String firstName, String lastName, String grade, String groupID, String projectID, String role, String finalNotes) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.groupID = groupID;
        this.projectID = projectID;
        this.role = role;
        this.finalNotes = finalNotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFinalNotes() {
        return finalNotes;
    }

    public void setFinalNotes(String finalNotes) {
        this.finalNotes = finalNotes;
    }

    public String toString() {

        return (this.getFirstName() + " " + this.getLastName() + " - " + this.getId());
    }
}
