package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProjectScreen extends AppCompatActivity {

    DatabaseHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_screen);

        database = new DatabaseHelper(this);

        TextView titleText;
        TextView projectIdText;
        TextView projectTutorText;
        TextView projectClientText;
        TextView projectUnitText;
        Button editProjectButton;

        titleText = (TextView) findViewById(R.id.titleText);
        projectIdText = (TextView) findViewById(R.id.projectIDText);
        projectTutorText = (TextView) findViewById(R.id.idText);
        projectClientText = (TextView) findViewById(R.id.projectClientText);
        projectUnitText = (TextView) findViewById(R.id.durationText);
        editProjectButton = (Button) findViewById(R.id.editProjectButton);

        // get project details from global variables
        Project p = getProjectDetails();
        titleText.setText(p.getTitle());
        projectIdText.setText(p.getId());


        if(!p.getTutor().isEmpty()) {
            projectTutorText.setText(p.getTutor());
        }else{
            projectTutorText.setText("N/A");
        }


        if(!p.getClient().isEmpty()) {
            Client thisClient = database.getClientDetails(p.getId());
            String clientDetails = "Name: "+thisClient.getFullName() +"\n"
                    +"Email: "+thisClient.getEmail()+"\n"
                    +"Phone: "+thisClient.getPhone()+"\n"
                    +"Address: "+thisClient.getAddress();

            projectClientText.setText(clientDetails);
        }
        else{
            projectClientText.setText("N/A");
        }

        projectUnitText.setText(p.getUnit());


        editProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditProjectScreen();
            }
        });
    }


    private Project getProjectDetails(){
        return ((GlobalVariables) this.getApplication()).getProject();
    }


    public void openEditProjectScreen(){
        Intent intent = new Intent(this, EditProject.class);
        startActivity(intent);
        finish();
    }


    public void onBackPressed(){
        Intent intent = new Intent(this, AllProjects.class);
        startActivity(intent);
        finish();
    }

}