package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AllProjects extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper database;
    private ArrayList<Project> projectsList;
    private Button addProjectButton;
    private TextView noProjectsFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_projects);

        listView = (ListView) findViewById(R.id.listView);
        database = new DatabaseHelper(this);
        addProjectButton = (Button) findViewById(R.id.addProjectButton);
        noProjectsFound = (TextView) findViewById(R.id.noProjectsText);


        projectsList = database.getProjectsList();

        if(projectsList.isEmpty()) noProjectsFound.setText("No Projects Found");
        else noProjectsFound.setText("");

        ArrayAdapter<Project> adapter;
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_selectable_list_item, projectsList);
        listView.setAdapter(adapter);


        //open selected project on a new screen
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Project p = (Project) o;
                setProject(p);
                openThisProject();
            }
        });


        addProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddProjectPage();
            }
        });
    }


    public void setProject(Project p){
        ((GlobalVariables) this.getApplication()).setProject(p);
    }


    public void openThisProject(){
        Intent intent = new Intent(this, ProjectScreen.class);
        startActivity(intent);
        finish();
    }


    public void openAddProjectPage(){
        Intent intent = new Intent(this, CreateProject.class);
        startActivity(intent);
        finish();
    }


    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}