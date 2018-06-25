package com.example.lakshaysharma.simpsongrades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String FIREBASE_USERNAME = "lakshay.sharma994@gmail.com";
    private static final String FIREBASE_PASSWORD = "lakshay123";
    private FirebaseAuth Auth;

    private EditText NAME;
    private EditText PASSWORD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NAME = findViewById(R.id.name);
        PASSWORD = findViewById(R.id.password);

        Auth = FirebaseAuth.getInstance();
        Auth.signInWithEmailAndPassword(FIREBASE_USERNAME, FIREBASE_PASSWORD);

    }

    public void loginClick(View view) {

        final String name = NAME.getText().toString();
        final String password = PASSWORD.getText().toString();

        DatabaseReference fireBase = FirebaseDatabase.getInstance().getReference();

        DatabaseReference students = fireBase.child("simpsons/students");
        Query person = students.orderByChild("name").equalTo(name);
        person.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Student student = dataSnapshot.getChildren().iterator().next().getValue(Student.class);
                Log.d("student: ", student.password);
                String correctPassword = student.password;
                if (password.equals(correctPassword)){
                    Intent intent = new Intent(MainActivity.this, GradesActivity.class);
                    intent.putExtra("name", student.name);
                    intent.putExtra("id", student.id);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("OnCancelled: ", databaseError.toString());
            }
        });
    }
}
