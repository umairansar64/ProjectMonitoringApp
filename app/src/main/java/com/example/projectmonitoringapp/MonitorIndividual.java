package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MonitorIndividual extends AppCompatActivity {

    TextView weekText;
    TextView studentContribution;
    TextView notes;
    DatabaseHelper database;
    Spinner studentsSpinner;
    CheckBox attendanceBox;
    Button saveButton;
    Button goBackButton;
    StudentInGroup thisStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_individual);

        weekText = (TextView) findViewById(R.id.weekNumText);
        studentContribution = (TextView) findViewById(R.id.contribution);
        notes = (TextView) findViewById(R.id.additionalNotes);
        database = new DatabaseHelper(this);
        studentsSpinner = (Spinner) findViewById(R.id.spinnerStudents);
        attendanceBox = (CheckBox) findViewById(R.id.checkBox);
        saveButton = (Button) findViewById(R.id.saveBtn);
        goBackButton = (Button) findViewById(R.id.goBackBtn);

        final Group g = getGroupDetails();
        weekText.setText("Week: " + getWeekNumber() + " (" + getThisWeeksStartDate() + ")");

        final ArrayList<StudentInGroup> studentsList = database.getStudentsInGroup(g.getId());

        ArrayAdapter<StudentInGroup> adapter;
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, studentsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentsSpinner.setAdapter(adapter);

        // when student selection changed, update the information
        studentsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                final Group g = getGroupDetails();

                StudentInGroup thisStudent = (StudentInGroup) studentsSpinner.getSelectedItem();

                StudentWeek thisStudentWeek = database.getStudentWeek(g.getId(), thisStudent.getStudentId(), getWeekNumber());

                attendanceBox.setChecked(false);
                if(thisStudentWeek.isPresent() == 1) attendanceBox.setChecked(true);
                studentContribution.setText(thisStudentWeek.getContribution());
                notes.setText(thisStudentWeek.getNotes());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // save information for current student
                thisStudent =  (StudentInGroup) studentsSpinner.getSelectedItem();
                int present = 0;
                if(attendanceBox.isChecked()) present = 1;

                boolean isUpdated = database.updateStudentWeek(getWeekNumber(),thisStudent.getStudentId(),present,studentContribution.getText().toString(),notes.getText().toString(),g.getId());

                if(isUpdated) Toast.makeText(MonitorIndividual.this, "Updated", Toast.LENGTH_LONG).show();
                else Toast.makeText(MonitorIndividual.this, "Couldn't update", Toast.LENGTH_LONG).show();
            }
        });


        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMonitorGroupScreen();
            }
        });
    }


    private int getWeekNumber(){
        return ((GlobalVariables) this.getApplication()).getWeek();
    }


    private Group getGroupDetails(){
        return ((GlobalVariables) this.getApplication()).getGroup();
    }


    private String getThisWeeksStartDate(){
        return ((GlobalVariables) this.getApplication()).getStartDate();
    }


    private void openMonitorGroupScreen(){
        Intent intent = new Intent(this, MonitorGroup.class);
        startActivity(intent);
        finish();
    }


    public void onBackPressed(){
        Intent intent = new Intent(this, MonitorGroup.class);
        startActivity(intent);
        finish();
    }

}