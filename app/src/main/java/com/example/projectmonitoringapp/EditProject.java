package com.example.projectmonitoringapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);

        final EditText projectIdInput;
        final EditText projectTitleInput;
        final EditText projectTutorInput;
        final EditText projectClientName;
        final EditText projectClientEmail;
        final EditText projectClientPhone;
        final EditText projectClientAddress;
        final EditText projectUnitInput;
        Button cancelButton;
        Button saveButton;
        Button deleteButton;
        final DatabaseHelper database;
        final TextView errorMessage;
        final Validate validate;

        projectIdInput = (EditText) findViewById(R.id.projectIDInput);
        projectTitleInput = (EditText) findViewById(R.id.projectTitleInput);
        projectTutorInput = (EditText) findViewById(R.id.projectTutorInput);
        projectClientName = (EditText) findViewById(R.id.projectClientNameInput);
        projectClientEmail = (EditText) findViewById(R.id.projectClientEmailInput);
        projectClientPhone = (EditText) findViewById(R.id.projectClientPhoneInput);
        projectClientAddress = (EditText) findViewById(R.id.projectClientAddressInput);
        projectUnitInput = (EditText) findViewById(R.id.projectUnitInput);
        cancelButton = (Button) findViewById(R.id.goBackBtn);
        saveButton = (Button) findViewById(R.id.saveBtn);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        errorMessage = (TextView) findViewById(R.id.errorText);
        database = new DatabaseHelper(this);
        validate = new Validate();


        final Project thisProject = getProjectDetails();
        Client client = database.getClientDetails(thisProject.getId());

        projectIdInput.setText(thisProject.getId());
        projectIdInput.setEnabled(false);
        projectTitleInput.setText(thisProject.getTitle());
        projectTutorInput.setText(thisProject.getTutor());
        projectClientName.setText(thisProject.getClient());
        projectClientEmail.setText(client.getEmail());
        projectClientPhone.setText(client.getPhone());
        projectClientAddress.setText(client.getAddress());
        projectUnitInput.setText(thisProject.getUnit());

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProjectScreen();
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = projectTitleInput.getText().toString();
                String id = projectIdInput.getText().toString();
                String tutor = projectTutorInput.getText().toString();
                String clientName = projectClientName.getText().toString();
                String clientEmail = projectClientEmail.getText().toString();
                String clientPhone = projectClientPhone.getText().toString();
                String clientAddress = projectClientAddress.getText().toString();
                String unit = projectUnitInput.getText().toString();

                // validate all the entries
                if (!validate.isTermValid(title)
                        || !validate.isTermValid(id)
                        || !validate.isTermValid(clientName)
                        || !validate.isEmailValid(clientEmail)
                        || (!tutor.isEmpty() && !validate.isTermValid(tutor))
                        || (!clientPhone.isEmpty() && !validate.isTermValid(clientPhone))
                        || (!unit.isEmpty() && !validate.isTermValid(unit))) {

                    errorMessage.setText("Please fill in the required fields correctly.");
                }
                else {

                    errorMessage.setText("");

                    if(database.updateProjectData(title,id,tutor,clientName,unit) && database.updateClientData(clientName,id,clientPhone,clientEmail,clientAddress)){
                        Toast.makeText(EditProject.this, "Details updated Successfully", Toast.LENGTH_LONG).show();
                        setProjectGlobal(title, id, tutor, clientName, unit);
                        openProjectScreen();
                    }
                    else {
                        Toast.makeText(EditProject.this, "Couldn't update details! ", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(database.isProjectAssociatedWithAGroup(projectIdInput.getText().toString())){
                    errorMessage.setText("Project Associated with a group cannot be deleted");
                }
                else {
                    errorMessage.setText("");
                    AlertDialog alertDialog = AskOption(thisProject.getId());
                    alertDialog.show();
                }
            }
        });
    }


    private AlertDialog AskOption(final String projectID)
    {
        final DatabaseHelper database = new DatabaseHelper(this);

        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Delete")
                .setMessage("Confirm Delete?")

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        if(database.deleteProjectData(projectID)>0){
                            Toast.makeText(EditProject.this, "Project deleted!", Toast.LENGTH_LONG).show();
                        }
                        openAllProjects();
                        dialog.dismiss();
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


    private Project getProjectDetails(){
        return ((GlobalVariables) this.getApplication()).getProject();
    }


    public void setProjectGlobal(String title, String id, String tutor, String client, String unit){
        Project p = new Project(title,id,tutor,client,unit);
        ((GlobalVariables) this.getApplication()).setProject(p);
    }


    public void openProjectScreen(){
        Intent intent = new Intent(this, ProjectScreen.class);
        startActivity(intent);
        finish();
    }


    private void openAllProjects(){
        Intent intent = new Intent(this, AllProjects.class);
        startActivity(intent);
        finish();
    }


    public void onBackPressed(){
        Intent intent = new Intent(this, ProjectScreen.class);
        startActivity(intent);
        finish();
    }
}