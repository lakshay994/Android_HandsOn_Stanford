package com.example.lakshaysharma.listfromfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private static Map<String, String> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dictionary = new HashMap<>();
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.words));
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            String[] parts = line.split("\t");
            dictionary.put(parts[0], parts[1]);
        }

        Spinner spin = findViewById(R.id.spinnerList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet()));
        spin.setAdapter(arrayAdapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String word = adapterView.getItemAtPosition(i).toString();
                String defn = dictionary.get(word);
                Toast.makeText(MainActivity.this, defn, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
