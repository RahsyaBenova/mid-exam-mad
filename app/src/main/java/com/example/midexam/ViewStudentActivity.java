package com.example.midexam;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewStudentActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    ListView listViewStudents;
    ArrayList<String> studentList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        myDb = new DatabaseHelper(this);
        listViewStudents = findViewById(R.id.listViewStudents);

        studentList = new ArrayList<>();
        Cursor cursor = myDb.getAllData();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data found in database", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String record = "Student ID: " + cursor.getString(0) + "\n" +
                        "Name: " + cursor.getString(1) + "\n" +
                        "Major: " + cursor.getString(2);
                studentList.add(record);
            }
        }

        // Bind data to ListView via ArrayAdapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        listViewStudents.setAdapter(adapter);
    }
}