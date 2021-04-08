package com.example.projectmonitoringapp;

public class StudentWeek {

    private int weekNum;
    private int studentID;
    private String contribution;
    private String notes;
    private int isPresent;

    public StudentWeek(int weekNum, int studentID, String contribution, String notes, int isPresent) {
        this.weekNum = weekNum;
        this.studentID = studentID;
        this.contribution = contribution;
        this.notes = notes;
        this.isPresent = isPresent;
    }

    public int getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(int weekNum) {
        this.weekNum = weekNum;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int isPresent() {
        return isPresent;
    }

    public void setPresent(int present) {
        isPresent = present;
    }
}
