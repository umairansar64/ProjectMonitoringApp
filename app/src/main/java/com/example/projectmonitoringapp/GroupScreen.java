package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class GroupScreen extends AppCompatActivity {

    TextView groupId;
    TextView groupProject;
    TextView groupClient;
    DatabaseHelper database;
    Button addStudentsButton;
    Button monitorGroupsButton;
    Button editGroupButton;
    Button assignGradesButton;
    TextView groupMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_screen);

        groupId = (TextView) findViewById(R.id.groupIdText);
        groupProject = (TextView) findViewById(R.id.groupProjectText);
        groupClient = (TextView) findViewById(R.id.groupClientText);
        groupMembers = (TextView) findViewById(R.id.groupMembersText);
        database = new DatabaseHelper(this);
        addStudentsButton = (Button) findViewById(R.id.addStudentButton);
        monitorGroupsButton = (Button) findViewById(R.id.monitorGroupButton);
        editGroupButton = (Button) findViewById(R.id.editGroupBtn);
        assignGradesButton = (Button) findViewById(R.id.assignGradesBtn);


        Group thisgroup = getGroupDetails();

        groupId.setText(thisgroup.getId());
        groupProject.setText((thisgroup.getProjectTitle()));

        Client thisClient = database.getClientDetails(thisgroup.getProjectID());
        groupClient.setText(thisClient.getFullName()+"\n"+thisClient.getEmail());
        String members = "";

        ArrayList<StudentInGroup> studentInGroups = database.getStudentsInGroup(thisgroup.getId());

        for(StudentInGroup sig : studentInGroups){
            members += sig.getFullName() + " - " + sig.getStudentRole() +'\n';
        }

        groupMembers.setText(members);


        addStudentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddStudentScreen();
            }
        });


        editGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditGroupScreen();
            }
        });


        assignGradesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAssignGradeScreen();
            }
        });


        monitorGroupsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMonitorGroupScreen();
            }
        });
    }


    private Group getGroupDetails(){
        return ((GlobalVariables) this.getApplication()).getGroup();
    }


    private void openAddStudentScreen(){
        Intent intent = new Intent(this, AddStudent.class);
        startActivity(intent);
        finish();
    }


    private void openMonitorGroupScreen(){
        Intent intent = new Intent(this, MonitorGroup.class);
        startActivity(intent);
        finish();
    }


    private void openEditGroupScreen(){
        Intent intent = new Intent(this, EditGroup.class);
        startActivity(intent);
        finish();
    }


    private void openAssignGradeScreen(){
        Intent intent = new Intent(this, AssignGrades.class);
        startActivity(intent);
        finish();
    }


    public void onBackPressed(){
        Intent intent = new Intent(this, AllGroups.class);
        startActivity(intent);
        finish();
    }
}