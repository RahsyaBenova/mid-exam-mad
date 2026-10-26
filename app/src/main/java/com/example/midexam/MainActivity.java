package com.example.midexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName, editID;
    Spinner spinnerMajor;
    Button btnAdd, btnView, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = findViewById(R.id.editTextName);
        editID = findViewById(R.id.editTextID);
        spinnerMajor = findViewById(R.id.spinnerMajor);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        // Populate Spinner options for Case 1 (Informatics / Information Systems)
        String[] majors = {"Informatics", "Information Systems"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, majors);
        spinnerMajor.setAdapter(adapter);

        addData();
        viewData();
        updateData();
        deleteData();
    }

    public void addData() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editID.getText().toString().trim();
                String name = editName.getText().toString().trim();
                String major = spinnerMajor.getSelectedItem().toString();

                if (id.isEmpty() || name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isInserted = myDb.insertData(id, name, major);
                if (isInserted) {
                    Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(MainActivity.this, "Error: Student ID must be unique!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void viewData() {
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Opens a new page (ViewStudentActivity) to display the list of data
                Intent intent = new Intent(MainActivity.this, ViewStudentActivity.class);
                startActivity(intent);
            }
        });
    }

    public void updateData() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editID.getText().toString().trim();
                String name = editName.getText().toString().trim();
                String major = spinnerMajor.getSelectedItem().toString();

                if (id.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter Student ID to update", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isUpdate = myDb.updateData(id, name, major);
                if (isUpdate) {
                    Toast.makeText(MainActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(MainActivity.this, "Update Failed (ID not found)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void deleteData() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editID.getText().toString().trim();
                if (id.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter Student ID to delete", Toast.LENGTH_SHORT).show();
                    return;
                }

                Integer deletedRows = myDb.deleteData(id);
                if (deletedRows > 0) {
                    Toast.makeText(MainActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(MainActivity.this, "Deletion Failed (ID not found)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearFields() {
        editID.setText("");
        editName.setText("");
        spinnerMajor.setSelection(0);
    }
}