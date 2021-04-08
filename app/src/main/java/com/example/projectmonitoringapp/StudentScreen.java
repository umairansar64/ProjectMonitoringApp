package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentScreen extends AppCompatActivity {

    TextView studentId;
    TextView name;
    TextView grade;
    TextView group;
    TextView project;
    Button goBackButton;
    DatabaseHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_screen);

        studentId = (TextView) findViewById(R.id.studentIdText);
        name = (TextView) findViewById(R.id.studentNameText);
        grade = (TextView) findViewById(R.id.studentGradeText);
        group = (TextView) findViewById(R.id.studentGroupText);
        project = (TextView) findViewById(R.id.studentProjectText);
        goBackButton = (Button) findViewById(R.id.goBackBtn);
        database = new DatabaseHelper(this);

        // get student details from Global Variables
        Student s = getStudentDetails();
        studentId.setText("ID: " + s.getId());
        name.setText(s.getFirstName() + " " + s.getLastName());
        if(!s.getGrade().isEmpty()) grade.setText(s.getGrade());

        String studentsGroupId = database.getGroupIdForStudent(s.getId());
        group.setText(studentsGroupId);
        String studentsProjectId = database.getProjectIdForGroup(studentsGroupId);
        project.setText(database.getProjectDetails(studentsProjectId).getTitle());

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAllStudentsScreen();
            }
        });
    }


    private Student getStudentDetails(){
        return ((GlobalVariables) this.getApplication()).getStudent();
    }


    private void openAllStudentsScreen() {
        Intent intent = new Intent(this, AllStudents.class);
        startActivity(intent);
        finish();
    }


    public void onBackPressed(){
        Intent intent = new Intent(this, AllStudents.class);
        startActivity(intent);
        finish();
    }
}