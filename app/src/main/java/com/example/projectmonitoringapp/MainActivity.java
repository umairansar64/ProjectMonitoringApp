package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button projectsButton;
    Button allStudentsButton;
    Button groupsButton;
    DatabaseHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        projectsButton = (Button) findViewById(R.id.projectsButton);
        allStudentsButton = (Button) findViewById(R.id.studentsButton);
        groupsButton = (Button) findViewById(R.id.groupsButton);
        database = new DatabaseHelper(this);

        //Uncomment the line below to generate sample data
        //createSampleData();

        projectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProjectsList();
            }
        });
        groupsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGroupsList();
            }
        });
        allStudentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStudentsList();
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // add sample data to the database (uncomment the call on line 28 to generate sample data)

    public void createSampleData(){
        createSampleProjects();
        createSampleGroups();
        createSampleStudents();
        addSampleStudentsInGroups();
        addSampleGroupWeek();
        createSampleClients();
    }

    public void createSampleProjects(){
        database.insertProjectData("Ravens Bikes","6GWZ6464","Elliene","Stephen Hawkings","Information Systems");
        database.insertProjectData("Developing Your Professionalism","6GWZ4698","Sandra Brooks","Mathew Crossley","Professional Development");
    }

    public void createSampleClients(){
        database.insertClientData("Stephen Hawkings","6GWZ6464","","stephen@gmail.com","");
        database.insertClientData("Sandra Brooks","6GWZ4698","","sandra@gmail.com","");
    }

    public void createSampleGroups(){
        database.insertGroupData("AG7643","6GWZ6464",26,"16/09/2020");
        database.insertGroupData("AG7883","6GWZ4698",26,"08/01/2021");
    }

    public void createSampleStudents(){
        database.insertStudentData(18083274,"Hamza","Nazir","");
        database.insertStudentData(18083233,"Badar","Ahmed","");
        database.insertStudentData(18083234,"Luke","Wright","");
        database.insertStudentData(18086434,"Andre","Wilson","");
    }

    public void addSampleStudentsInGroups(){
        database.insertStudentInGroup(18083274,"Project Manager","AG7643");
        database.insertStudentInGroup(18083233,"Developer","AG7883");
        database.insertStudentInGroup(18083234,"Project Manager","AG7883");
        database.insertStudentInGroup(18086434,"Project Analyst","AG7883");

        for(int i = 1; i<3;i++){
            database.insertStudentWeek(i, 18083233,1,"","","AG7883",1);
            database.insertStudentWeek(i, 18083234,1,"","","AG7883",1);
            database.insertStudentWeek(i, 18086434,1,"","","AG7883",1);
        }

        for(int i = 3; i<=26;i++){
            database.insertStudentWeek(i, 18083233,0,"","","AG7883",0);
            database.insertStudentWeek(i, 18083234,0,"","","AG7883",0);
            database.insertStudentWeek(i, 18086434,0,"","","AG7883",0);
        }
        for(int i = 1; i<=26;i++){
            database.insertStudentWeek(i, 18083274,0,"","","AG7643",0);
        }
    }

    public void addSampleGroupWeek(){

        database.insertGroupWeek(1,"AG7883","start work",0,"work started","very well done","could not contact client",1);
        database.insertGroupWeek(2,"AG7883","finish work",1,"work finished","good work","no issues",1);

        for(int i = 3; i<=26;i++){

            database.insertGroupWeek(i,"AG7883","",0,"","","",0);
        }

        for(int i = 1; i<=26;i++){

            database.insertGroupWeek(i,"AG7643","",0,"","","",0);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void openProjectsList(){
        Intent intent = new Intent(this, AllProjects.class);
        startActivity(intent);
        finish();
    }

    public void openGroupsList(){
        Intent intent = new Intent(this, AllGroups.class);
        startActivity(intent);
        finish();
    }

    public void openStudentsList(){
        Intent intent = new Intent(this, AllStudents.class);
        startActivity(intent);
        finish();
    }

}
