package com.example.projectmonitoringapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditGroup extends AppCompatActivity {

    DatabaseHelper database;
    LinearLayout studentsListLayout;
    EditText projectId;
    EditText duration;
    EditText  date;
    CheckBox checkBox;
    Button saveButton;
    Button goBackButton;
    Button removeButton;
    TextView errorMessage; // for editing group details section
    TextView errorMessage2; // for removing student section
    Validate validate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_group);

        database = new DatabaseHelper(this);
        studentsListLayout = (LinearLayout) findViewById(R.id.listStudentsLayout);
        projectId = (EditText) findViewById(R.id.projectIDInput);
        duration = (EditText) findViewById(R.id.durationInput2);
        date = (EditText) findViewById(R.id.startDateInput);
        errorMessage = (TextView) findViewById(R.id.errorMessageText);
        errorMessage2 = (TextView) findViewById(R.id.errorMessageText2);
        saveButton = (Button) findViewById(R.id.saveBtn);
        goBackButton = (Button) findViewById(R.id.goBackBtn);
        removeButton = (Button) findViewById(R.id.removeBtn);
        validate = new Validate();

        final Group g = getGroupDetails();

        errorMessage.setText("");
        errorMessage2.setText("");
        projectId.setText(g.getProjectID());
        duration.setText(g.getDuration()+"");
        date.setText(g.getStartDate());


        refreshStudentsList();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validate.isTermValid(projectId.getText().toString())
                    || duration.getText().toString().isEmpty()
                    || Integer.parseInt(duration.getText().toString())<1
                    || !validate.isDateValid(date.getText().toString())){
                    errorMessage.setText("Fill in the required fields correctly");
                }
                else if(!database.doesProjectExist(projectId.getText().toString())){ // if project does not exist
                    errorMessage.setText("Project with this ID doesn't exist");
                }
                else{

                    boolean isUpdated = database.updateGroupDetails(g.getId(),
                            projectId.getText().toString(),
                            Integer.parseInt(duration.getText().toString()),
                            date.getText().toString());

                    if(isUpdated) {
                        updateGroupGlobalVariable(new Group(g.getId(),
                                projectId.getText().toString(),
                                database.getProjectDetails(projectId.getText().toString()).getTitle(),
                                database.getProjectDetails(projectId.getText().toString()).getClient(),
                                Integer.parseInt(duration.getText().toString()),
                                date.getText().toString()
                                )
                        );
                        Toast.makeText(EditGroup.this, "Update Successful", Toast.LENGTH_LONG).show();
                        errorMessage.setText("");
                    }
                    else Toast.makeText(EditGroup.this, "Could not update", Toast.LENGTH_LONG).show();
                }
            }
        });


        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean nothingSelected = true;

                for(int i = 0; i < studentsListLayout.getChildCount(); i++){
                    CheckBox student = (CheckBox) studentsListLayout.getChildAt(i);
                    if(student.isChecked()) {
                        nothingSelected = false;
                    }
                }

                if(!nothingSelected) {
                    errorMessage2.setText("");
                    AlertDialog alertDialog = AskOption();
                    alertDialog.show();
                }
                else{
                    errorMessage2.setText("Nothing Selected");
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

    // popup displayed to confirm removal of students from group
    private AlertDialog AskOption()
    {
        final DatabaseHelper database = new DatabaseHelper(this);

        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Warning!")
                .setMessage("You could lose all the student data.")

                .setPositiveButton("Remove", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        for(int i = 0; i < studentsListLayout.getChildCount(); i++) {
                            CheckBox student = (CheckBox) studentsListLayout.getChildAt(i);
                            int studentId = student.getId();
                            if (student.isChecked()) {
                                if (database.removeStudent(studentId + "") > 0)
                                    Toast.makeText(EditGroup.this, "Student(s) removed", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(EditGroup.this, "Could not remove Student(s)", Toast.LENGTH_LONG).show();

                                refreshStudentsList();
                            }
                        }
                    }
                })

                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })

                .create();

        return myQuittingDialogBox;
    }

    private void refreshStudentsList() {

        Group g = getGroupDetails();

        ArrayList<StudentInGroup> studentsList = database.getStudentsInGroup(g.getId());

        studentsListLayout.removeAllViews();

        for (StudentInGroup sig : studentsList) {

            checkBox = new CheckBox(this);
            checkBox.setId(sig.getStudentId());
            checkBox.setText(sig.getFullName());
            checkBox.setTextSize(24);
            checkBox.setTextColor(Color.DKGRAY);
            checkBox.setChecked(false);

            studentsListLayout.addView(checkBox);
        }
    }


    private Group getGroupDetails(){
        return ((GlobalVariables) this.getApplication()).getGroup();
    }


    private int getWeek(){
        return ((GlobalVariables) this.getApplication()).getWeek();
    }


    public void updateGroupGlobalVariable(Group g){
        ((GlobalVariables) this.getApplication()).setGroup(g);
    }


    private void openGroupScreen(){
        Intent intent = new Intent(this, GroupScreen.class);
        startActivity(intent);
    }


    public void onBackPressed(){
        Intent intent = new Intent(this, GroupScreen.class);
        startActivity(intent);
        finish();
    }

}