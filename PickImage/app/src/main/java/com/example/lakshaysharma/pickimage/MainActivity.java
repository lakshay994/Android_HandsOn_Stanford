package com.example.lakshaysharma.pickimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pickTurtle(View view) {
        ImageView img = findViewById(R.id.imgId);
        int id = view.getId();

        if (id == R.id.leo){
            img.setImageResource(R.drawable.tmntleo);
        }
        else if (id == R.id.mike){
            img.setImageResource(R.drawable.tmntmike);
        }
        else if (id == R.id.don){
            img.setImageResource(R.drawable.tmntdon);
        }
        else {
            img.setImageResource(R.drawable.tmntraph);
        }
    }
}
