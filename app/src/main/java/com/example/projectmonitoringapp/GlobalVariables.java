package com.example.projectmonitoringapp;

import android.app.Application;

public class GlobalVariables extends Application {

    private Project project;
    private Group group;
    private Student student;
    private String startDate;
    private int weekNum = 0;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setWeek(int week) {
        this.weekNum = week;
    }

    public int getWeek() {
        return weekNum;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
