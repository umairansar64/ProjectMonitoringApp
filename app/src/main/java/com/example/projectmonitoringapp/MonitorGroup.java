package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class MonitorGroup extends AppCompatActivity {

    Spinner spinnerWeek;
    ArrayList<String> weekNumbers;
    EditText lastWeekTask;
    EditText taskInput;
    EditText progressInput;
    EditText notesInput;
    EditText issuesInput;
    CheckBox taskCompleted;
    DatabaseHelper database;
    Button saveButton;
    Button goBackButton;
    Button switchButton;
    LinearLayout attendanceLayout;
    CheckBox checkBox;
    Validate validate;

    //TextView textView;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_group);

        spinnerWeek = (Spinner) findViewById(R.id.spinnerWeeks);
        weekNumbers = new ArrayList<String>();
        lastWeekTask = (EditText) findViewById(R.id.lastWeeksTask);
        taskInput = (EditText) findViewById(R.id.thisWeekstask);
        progressInput = (EditText) findViewById(R.id.progress);
        notesInput = (EditText) findViewById(R.id.notes);
        issuesInput = (EditText) findViewById(R.id.issues);
        taskCompleted = (CheckBox) findViewById(R.id.taskCheckBox);
        saveButton = (Button) findViewById(R.id.saveBtn);
        goBackButton = (Button) findViewById(R.id.goBackBtn);
        switchButton = (Button) findViewById(R.id.switchBtn);
        database = new DatabaseHelper(this);
        attendanceLayout = (LinearLayout) findViewById(R.id.layoutAttendance);
        validate = new Validate();


        final Group thisgroup = getGroupDetails();
        String startDate = thisgroup.getStartDate();

        // prepare weeks spinner text
        for(int i=1;i<=thisgroup.getDuration();i++){
            weekNumbers.add("Week "+i+" - "+startDate);
            // increment date by 7 days everytime
            LocalDate newDate = LocalDate.parse(validate.getDateInYMDformat(startDate)).plusDays(7);
            startDate = validate.getDateInDMYformat(newDate.toString());
        }

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, weekNumbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWeek.setAdapter(adapter);

        spinnerWeek.setSelection(getWeek() - 1); // by default set selection as week 1

        spinnerWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                displayWeek(spinnerWeek.getSelectedItemPosition() + 1);
                setWeek(spinnerWeek.getSelectedItemPosition() + 1);
                setWeeksStartDate(spinnerWeek.getSelectedItemPosition() + 1, thisgroup.getStartDate());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // not used in this case
            }
        });


        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGroupScreen();
            }
        });


        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openIndividualStudentNotes();
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int taskComplete = 0;
                if(taskCompleted.isChecked()) taskComplete = 1;

                int weekNum = spinnerWeek.getSelectedItemPosition() + 1;

                boolean isInserted = database.updateGroupWeek(weekNum,
                        thisgroup.getId(),taskInput.getText().toString(),
                        taskComplete,
                        progressInput.getText().toString(),
                        notesInput.getText().toString(),
                        issuesInput.getText().toString(),
                        1
                        );

                for(int i = 0; i < attendanceLayout.getChildCount(); i++){

                    CheckBox student = (CheckBox) attendanceLayout.getChildAt(i);

                    int studentId = student.getId();

                    int hasAttended = 0;
                    if(student.isChecked()) hasAttended = 1;
                    database.updateStudentWeek(weekNum,
                            studentId,
                            hasAttended,
                            "",
                            "",
                            thisgroup.getId());
                }

                if(isInserted) Toast.makeText(MonitorGroup.this, "Week Updated", Toast.LENGTH_LONG).show();
                else Toast.makeText(MonitorGroup.this, "Couldn't update week Information", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void displayWeek(int weekNum) {

        Group g = getGroupDetails();

        GroupWeek thisGroupWeek = database.getGroupWeek(g.getId(), weekNum);

        taskInput.setText(thisGroupWeek.getTask());
        progressInput.setText(thisGroupWeek.getProgress());
        notesInput.setText(thisGroupWeek.getNotes());
        issuesInput.setText(thisGroupWeek.getIssues());
        taskCompleted.setChecked(false);
        if(thisGroupWeek.isTaskCompleted() == 1){
            taskCompleted.setChecked(true);
        }

        // if its not the first week get the task given in last week
        if (weekNum > 1) {
            GroupWeek previousWeek = database.getGroupWeek(g.getId(), weekNum - 1);
            lastWeekTask.setText(previousWeek.getTask());
        } else {
            lastWeekTask.setText("");
        }

        ArrayList<StudentInGroup> studentsInGroup = database.getStudentsInGroup(g.getId());

        attendanceLayout.removeAllViews();

        // create attendance checkboxes for all the students in group
        for (StudentInGroup sig : studentsInGroup) {

            checkBox = new CheckBox(this);
            checkBox.setId(sig.getStudentId());
            checkBox.setText(sig.getFullName());
            checkBox.setTextSize(24);
            checkBox.setTextColor(Color.DKGRAY);
            StudentWeek thisWeek = database.getStudentWeek(g.getId(), sig.getStudentId(), weekNum);
            checkBox.setChecked(false);
            if (thisWeek.isPresent() == 1) {
                checkBox.setChecked(true);
            }
            attendanceLayout.addView(checkBox);
        }
    }


    private Group getGroupDetails(){
        return ((GlobalVariables) this.getApplication()).getGroup();
    }


    public void setWeek(int week){
        ((GlobalVariables) this.getApplication()).setWeek(week);
    }


    public void setWeeksStartDate(int weekNum, String monitoringStarted){

        LocalDate date = LocalDate.parse(validate.getDateInYMDformat(monitoringStarted));
        LocalDate dateAfterAddingDays = date.plusDays((weekNum - 1)*7);
        ((GlobalVariables) this.getApplication()).setStartDate(validate.getDateInDMYformat(dateAfterAddingDays.toString()));
    }


    private int getWeek(){
        return ((GlobalVariables) this.getApplication()).getWeek();
    }


    private void openGroupScreen(){
        Intent intent = new Intent(this, GroupScreen.class);
        startActivity(intent);
        finish();
    }


    public void openIndividualStudentNotes(){
        Intent intent = new Intent(this, MonitorIndividual.class);
        startActivity(intent);
        finish();
    }


    public void onBackPressed(){
        Intent intent = new Intent(this, GroupScreen.class);
        startActivity(intent);
        finish();
    }

}