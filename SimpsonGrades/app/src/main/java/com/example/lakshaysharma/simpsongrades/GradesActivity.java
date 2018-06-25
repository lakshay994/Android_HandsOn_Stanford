package com.example.lakshaysharma.simpsongrades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GradesActivity extends AppCompatActivity {

    private ArrayList<String> grades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        grades = new ArrayList<>();

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");

        DatabaseReference fireBase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference students = fireBase.child("simpsons/grades");
        Query query = students.orderByChild("student_id").equalTo(id);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    Grade grade = child.getValue(Grade.class);
                    grades.add(grade.grade + " in " + grade.course_name);
                    Log.d("Grades: ", grades.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("onCancelled: ", databaseError.toString());
            }
        });

        ListView list = findViewById(R.id.gradelist);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                grades);
        list.setAdapter(adapter);
    }
}
