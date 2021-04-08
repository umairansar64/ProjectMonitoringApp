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

public class AllGroups extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper database;
    private ArrayList<Group> groupsList;
    private Button addGroupButton;
    private TextView noGroupsFound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_groups);

        listView = (ListView) findViewById(R.id.listView);
        database = new DatabaseHelper(this);
        addGroupButton = (Button) findViewById(R.id.addGroupButton);
        noGroupsFound = (TextView) findViewById(R.id.noGroupsText);

        groupsList = database.getGroupsList();

        if(groupsList.isEmpty()) noGroupsFound.setText("No Groups Found");
        else noGroupsFound.setText("");

        for(Group g : groupsList){
            Project thisProject = database.getProjectDetails(g.getProjectID());
            g.setProjectTitle(thisProject.getTitle());
            g.setClient(thisProject.getClient());
        }


        ArrayAdapter<Group> adapter;
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_selectable_list_item, groupsList);
        listView.setAdapter(adapter);


        // when group from the list selected open group on new screen
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Group g = (Group) o;
                setGroup(g);
                openThisGroup();
            }
        });


        addGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddGroupPage();
            }
        });
    }


    public void setGroup(Group g){
        ((GlobalVariables) this.getApplication()).setGroup(g);
    }


    public void openThisGroup(){
        Intent intent = new Intent(this, GroupScreen.class);
        startActivity(intent);
        finish();
    }


    public void openAddGroupPage(){
        Intent intent = new Intent(this, CreateGroup.class);
        startActivity(intent);
        finish();
    }


    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}