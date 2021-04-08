package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateProject extends AppCompatActivity {

    Button saveButton;
    Button cancelButton;
    TextView errorMessage;
    EditText projectTitle;
    EditText projectId;
    EditText projectTutor;
    EditText projectClientName;
    EditText projectClientEmail;
    EditText projectClientPhone;
    EditText projectClientAddress;
    EditText projectUnit;
    Validate validate;
    DatabaseHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        saveButton = (Button) findViewById(R.id.saveBtn);
        cancelButton = (Button) findViewById(R.id.goBackBtn);
        errorMessage = (TextView) findViewById(R.id.errorText);
        projectTitle = (EditText) findViewById(R.id.projectTitleInput);
        projectId = (EditText) findViewById(R.id.projectIDInput);
        projectUnit = (EditText) findViewById(R.id.projectUnitInput);
        projectTutor = (EditText) findViewById(R.id.projectTutorInput);
        projectClientName = (EditText) findViewById(R.id.projectClientNameInput);
        projectClientEmail = (EditText) findViewById(R.id.projectClientEmailInput);
        projectClientPhone = (EditText) findViewById(R.id.projectClientPhoneInput);
        projectClientAddress = (EditText) findViewById(R.id.projectClientAddressInput);
        validate = new Validate();


        database = new DatabaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // validate all the entries
                if (!validate.isTermValid(projectTitle.getText().toString())
                        || !validate.isTermValid(projectId.getText().toString())
                        || !validate.isTermValid(projectClientName.getText().toString())
                        || !validate.isEmailValid(projectClientEmail.getText().toString())
                        || (!projectTutor.getText().toString().isEmpty() && !validate.isTermValid(projectTutor.getText().toString()))
                        || (!projectClientPhone.getText().toString().isEmpty() && !validate.isTermValid(projectClientPhone.getText().toString()))
                        || (!projectUnit.getText().toString().isEmpty() && !validate.isTermValid(projectUnit.getText().toString()))) {

                    errorMessage.setText("Please fill in the required fields correctly.");
                }
                else {
                    errorMessage.setText("");
                    // save project details
                    boolean isInserted = database.insertProjectData(
                            projectTitle.getText().toString(),
                            projectId.getText().toString(),
                            projectTutor.getText().toString(),
                            projectClientName.getText().toString(),
                            projectUnit.getText().toString());

                    if(isInserted) {
                        // save client details
                        isInserted = database.insertClientData(
                                projectClientName.getText().toString(),
                                projectId.getText().toString(),
                                projectClientPhone.getText().toString(),
                                projectClientEmail.getText().toString(),
                                projectClientAddress.getText().toString());
                    }

                    if (isInserted){
                        Toast.makeText(CreateProject.this, "Project Added Successfully", Toast.LENGTH_LONG).show();
                        openAllProjectsScreen();
                    }
                    else Toast.makeText(CreateProject.this, "Project could not be added", Toast.LENGTH_LONG).show();
                }
            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAllProjectsScreen();
            }
        });
    }


    public void openAllProjectsScreen(){
        Intent intent = new Intent(this, AllProjects.class);
        startActivity(intent);
        finish();
    }


    public void onBackPressed(){
        Intent intent = new Intent(this, AllProjects.class);
        startActivity(intent);
        finish();
    }

}