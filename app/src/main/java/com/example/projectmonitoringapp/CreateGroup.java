package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateGroup extends AppCompatActivity {

    Button saveButton;
    Button cancelButton;
    TextView errorMessage;
    EditText groupID;
    EditText duration;
    Validate validate;
    DatabaseHelper database;
    Spinner spinner;
    EditText date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        saveButton = (Button) findViewById(R.id.saveBtn);
        cancelButton = (Button) findViewById(R.id.goBackBtn);
        errorMessage = (TextView) findViewById(R.id.errorText);
        groupID = (EditText) findViewById(R.id.groupIDInput);
        duration = (EditText) findViewById(R.id.durationInput);
        database = new DatabaseHelper(this);
        spinner = (Spinner) findViewById(R.id.spinnerProjects);
        date = (EditText) findViewById(R.id.dateInput);
        validate = new Validate();


        final ArrayList<Project> projectsList = database.getProjectsList();

        ArrayAdapter<Project> adapter;
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, projectsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String groupId = groupID.getText().toString();
                String weeks = duration.getText().toString();

                if(projectsList.isEmpty()){
                    errorMessage.setText("There needs to be a Project before creating a group.");
                }
                else if (!validate.isTermValid(groupId)
                        || (!validate.isTermValid(weeks))
                        || !validate.isDateValid(date.getText().toString())){

                    errorMessage.setText("Please fill in the required fields correctly.");
                }
                else {

                    Project p = (Project) (spinner.getSelectedItem());
                    errorMessage.setText("");

                    boolean isInserted = database.insertGroupData(groupId, p.getId(), Integer.parseInt(weeks), date.getText().toString());

                    // create database entries for all the group weeks to be monitored when a group is created
                    for(int i = 1;i<=Integer.parseInt(weeks);i++){
                        database.insertGroupWeek(i,groupId,"",0,"","","",0);
                    }

                    if (isInserted){
                        Toast.makeText(CreateGroup.this, "Group Added Successfully", Toast.LENGTH_LONG).show();
                        goAllGroupsScreen();
                    }
                    else Toast.makeText(CreateGroup.this, "Group could not be added", Toast.LENGTH_LONG).show();
                }
            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goAllGroupsScreen();
            }
        });
    }


    public void goAllGroupsScreen(){
        Intent intent = new Intent(this, AllGroups.class);
        startActivity(intent);
        finish();
    }


    public void onBackPressed() {
        Intent intent = new Intent(this, AllGroups.class);
        startActivity(intent);
        finish();
    }
}