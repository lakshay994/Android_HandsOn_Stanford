package com.example.lakshaysharma.spinnerlist;

import android.os.WorkSource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String[] WORDS = {"contemplate", "to think deeply", "Aggravate", "to annoy"};
    private Map<String, String > dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dictionary = new HashMap<>();

        //map WORDS to hashmap to create dictionary
        for (int i = 0; i< WORDS.length; i +=2){
            dictionary.put(WORDS[i], WORDS[i + 1]);
        }

        Spinner list = findViewById(R.id.spinnerList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet())
        );
        list.setAdapter(adapter);

        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
