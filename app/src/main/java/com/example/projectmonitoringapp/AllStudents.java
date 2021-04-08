package com.example.projectmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AllStudents extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper database;
    private ArrayList<Student> studentsList;
    private EditText searchText;
    private TextView noStudentsFound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);

        listView = (ListView) findViewById(R.id.listView);
        database = new DatabaseHelper(this);
        searchText = (EditText) findViewById(R.id.searchTextInput);
        noStudentsFound = (TextView) findViewById(R.id.noStudentsText);


        studentsList = database.getStudentsList();

        if(studentsList.isEmpty()) noStudentsFound.setText("No Students Found");
        else noStudentsFound.setText("");

        ArrayAdapter<Student> adapter;
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_selectable_list_item, studentsList);
        listView.setAdapter(adapter);


        // update the student list once text in the input box changed
        searchText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                studentsList.clear();

                // student ids with that contain given substring appear in search results
                studentsList = database.getSearchedStudents(searchText.getText().toString());

                ArrayAdapter<Student> adapter;
                adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_selectable_list_item, studentsList);
                listView.setAdapter(adapter);
            }
        });


        //open selected student on a new screen
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object o = listView.getItemAtPosition(position);
                Student s = (Student) o;
                setStudent(s);
                openStudentScreen();
            }
        });
    }

    //save student details to global variable
    public void setStudent(Student s){
        ((GlobalVariables) this.getApplication()).setStudent(s);
    }


    public void openStudentScreen(){
        Intent intent = new Intent(this, StudentScreen.class);
        startActivity(intent);
        finish();
    }


    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}