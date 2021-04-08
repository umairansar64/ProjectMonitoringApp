package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AssignGrades extends AppCompatActivity {

    Spinner studentsSpinner;
    DatabaseHelper database;
    Button saveButton;
    Button goBackButton;
    TextView notes;
    TextView attendance;
    EditText grade;
    Validate validate;
    int studentsId;
    TextView errorMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_grades);

        studentsSpinner = (Spinner) findViewById(R.id.spinnerStudents2);
        database = new DatabaseHelper(this);
        saveButton = (Button) findViewById(R.id.saveBtn2);
        goBackButton = (Button) findViewById(R.id.goBackBtn2);
        notes = (TextView) findViewById(R.id.notesText);
        attendance = (TextView) findViewById(R.id.attendanceText);
        errorMessage = (TextView) findViewById(R.id.errorMessageText2);
        grade = (EditText) findViewById(R.id.gradeInput);
        validate = new Validate();
        studentsId = 0;

        notes.setText("");

        errorMessage.setText("");

        final Group g = getGroupDetails(); // get details from global variable using function

        final ArrayList<StudentInGroup> studentsList = database.getStudentsInGroup(g.getId());

        ArrayAdapter<StudentInGroup> adapter;
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, studentsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentsSpinner.setAdapter(adapter);

        studentsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                StudentInGroup thisStudent = (StudentInGroup) studentsSpinner.getSelectedItem();

                studentsId = thisStudent.getStudentId();

                grade.setText(database.getStudentDetails(studentsId).getGrade());

                ArrayList<StudentWeek> studentWeeks = database.getAllWeeksForStudent(g.getId(), thisStudent.getStudentId());

                String notesInStringFormat = "";

                int weeksAttended = 0;

                for(StudentWeek sw : studentWeeks){

                    notesInStringFormat += "Week: " + sw.getWeekNum() + "\n";
                    if(sw.isPresent() == 1){
                        notesInStringFormat += "Attendance Status: Present" + "\n";
                        weeksAttended+=1;
                    }
                    else notesInStringFormat += "Attendance Status: Absent" + "\n";


                    if(sw.getContribution().isEmpty()) notesInStringFormat += "Contribution: N/A" + "\n";
                    else notesInStringFormat += "Contribution: " + sw.getContribution() + "\n";


                    if(sw.getNotes().isEmpty()) notesInStringFormat += "Additional Notes: N/A" + "\n\n";
                    else notesInStringFormat += "Additional Notes: " + sw.getNotes() + "\n\n";
                }

                notes.setText(notesInStringFormat);


                int groupWeeksMarked = database.getNumOfWeeksMarked(g.getId());
                int attendancePercentage = (int) (((float) weeksAttended/(float) groupWeeksMarked) * 100);


                // if the user marks a student before marking the group
                if(weeksAttended > groupWeeksMarked){
                    attendance.setText("Attendance: 100%");
                }
                else {
                    attendance.setText("Attendance: " + attendancePercentage + "%");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // not used in this case
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate.isGradeValid(grade.getText().toString())){

                    errorMessage.setText("");
                    if(database.updateStudentGrade(studentsId, grade.getText().toString())){
                        Toast.makeText(AssignGrades.this, "Details Entered", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(AssignGrades.this, "Couldn't Enter Details", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    errorMessage.setText("Enter a valid grade!");
                }
            }
        });


        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGroupScreen();
            }
        });
    }


    private int getWeekNumber(){
        return ((GlobalVariables) this.getApplication()).getWeek();
    }


    private Group getGroupDetails(){
        return ((GlobalVariables) this.getApplication()).getGroup();
    }


    private void openGroupScreen(){
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