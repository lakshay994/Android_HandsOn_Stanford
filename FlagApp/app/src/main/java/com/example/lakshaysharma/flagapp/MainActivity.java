package com.example.lakshaysharma.flagapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private static String[] COUNTRIES = {
                "Australia", "Brazil", "China", "Egypt", "France",
                "Germany", "Ghana", "Italy", "Japan", "Mexico", "Nepal",
                "Nigeria", "Spain", "United Kingdom", "United States"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(String country: COUNTRIES){
            addCountry(country);
        }

    }

    private void addCountry(final String countryName){

        View flag = getLayoutInflater().inflate(R.layout.flags, null);

        String country = countryName.replace(" ", "").toLowerCase();

        ImageButton image = flag.findViewById(R.id.flagImg);
        int imageID = getResources().getIdentifier(country, "drawable", getPackageName());

        image.setImageResource(imageID);

        GridLayout grid = findViewById(R.id.gridLayout);
        grid.addView(flag);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setMessage(countryName);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //do nothing

                    }
                });

                Dialog dialog = alert.create();
                dialog.show();

            }
        });

    }

}
