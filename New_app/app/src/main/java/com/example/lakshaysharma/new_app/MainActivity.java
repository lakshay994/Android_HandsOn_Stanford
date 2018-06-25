package com.example.lakshaysharma.new_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int rand1;
    private int rand2;
    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll();
    }

    public void roll(){
        Random random = new Random();
        rand1 = random.nextInt(9);
        rand2 = random.nextInt(9);
        while (rand2 == rand1){
            rand2 = random.nextInt(9);
        }

        Button left = (Button) findViewById(R.id.leftButton);
        Button right = (Button) findViewById(R.id.rightButton);
        left.setText(Integer.toString(rand1));
        right.setText(Integer.toString(rand2));
    }

    private void check(int num1,int num2){
        if (num1> num2){
            points++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        }
        else {
            points--;
            Toast.makeText(this, "Oops!", Toast.LENGTH_SHORT).show();
        }
        TextView tview = (TextView) findViewById(R.id.pointsID);
        tview.setText("Points: "+ points);
        roll();
    }


    public void leftClick(View view) {
        check(rand1, rand2);
    }


    public void rightClick(View view) {
        check(rand2, rand1);
    }
}
