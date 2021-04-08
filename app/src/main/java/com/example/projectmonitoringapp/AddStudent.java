package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {

    EditText firstname;
    EditText lastname;
    EditText studentId;
    EditText studentRole;
    Button addButton;
    Button cancelButton;
    Validate validate;
    TextView errorMessage;
    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        firstname = (EditText) findViewById(R.id.firstnameInput);
        lastname = (EditText) findViewById(R.id.lastnameInput);
        studentId = (EditText) findViewById(R.id.studentIdInput);
        studentRole = (EditText) findViewById(R.id.studentRoleInput);
        addButton = (Button) findViewById(R.id.addButton);
        cancelButton = (Button) findViewById(R.id.goBackBtn);
        validate = new Validate();
        errorMessage = (TextView) findViewById(R.id.errorText);
        database = new DatabaseHelper(this);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Group thisGroup = getGroupDetails();


                // if any of the terms not valid
                if (!validate.isTermValid(firstname.getText().toString())
                        || !validate.isTermValid(lastname.getText().toString())
                        || studentId.getText().toString().isEmpty()
                        || (!studentRole.getText().toString().isEmpty()) && !validate.isTermValid(studentRole.getText().toString())) {

                    errorMessage.setText("Please fill in the required fields correctly.");
                }
                // all terms valid
                else {
                    errorMessage.setText("");
                    database.insertStudentData(Integer.parseInt(studentId.getText().toString()),firstname.getText().toString(),lastname.getText().toString(),"");
                    database.insertStudentInGroup(Integer.parseInt(studentId.getText().toString()),studentRole.getText().toString(),thisGroup.getId());

                    // create database entries for all the student weeks to be monitored when a student is created
                    for(int i = 1;i<=thisGroup.getDuration();i++){
                        database.insertStudentWeek(i,Integer.parseInt(studentId.getText().toString()),0,"","",thisGroup.getId(),0);
                    }
                    openGroupScreen();
                    Toast.makeText(AddStudent.this, "Student Data Added", Toast.LENGTH_LONG).show();
                }


            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGroupScreen();
            }
        });
    }

    // get group object from global variables
    private Group getGroupDetails(){
        return ((GlobalVariables) this.getApplication()).getGroup();
    }


    public void openGroupScreen(){
        Intent intent = new Intent(this, GroupScreen.class);
        startActivity(intent);
        finish();
    }


    public void onBackPressed() {
        Intent intent = new Intent(this, GroupScreen.class);
        startActivity(intent);
        finish();
    }
}