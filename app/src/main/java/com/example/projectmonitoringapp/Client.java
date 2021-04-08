package com.example.projectmonitoringapp;

public class Client {

    private String fullName;
    private String projectID;
    private String phone;
    private String email;
    private String address;

    public Client(String fullName, String projectID, String phone, String email, String address) {
        this.fullName = fullName;
        this.projectID = projectID;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
