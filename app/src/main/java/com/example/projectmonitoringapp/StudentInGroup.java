package com.example.projectmonitoringapp;

public class StudentInGroup {

    private int studentId;
    private String fullName;
    private String studentRole;
    private String groupId;

    public StudentInGroup(int studentId, String fullName, String studentRole, String groupId) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.studentRole = studentRole;
        this.groupId = groupId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String firstName) {
        this.fullName = firstName;
    }

    public String getStudentRole() {
        return studentRole;
    }

    public void setStudentRole(String studentRole) {
        this.studentRole = studentRole;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String toString() {

        return fullName;
    }
}
