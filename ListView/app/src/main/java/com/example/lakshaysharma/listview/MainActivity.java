package com.example.lakshaysharma.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //declare an array of words that the app will use
    private String[] WORDS = {"contemplate",  "to think deeply"
    , "aggravate", "to annoy",
       "android" , "robot"};

    private Map<String, String> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate new hashmap
        dictionary = new HashMap<>();

        //put words in the hashmap from the array WORDS
        for (int i = 0; i<WORDS.length; i+=2){
            dictionary.put(WORDS[i], WORDS[i+1]);
        }

        ListView list = findViewById(R.id.simpleList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet())
        );
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String word = adapterView.getItemAtPosition(i).toString();
                String defn = dictionary.get(word);
                Toast.makeText(MainActivity.this, defn, Toast.LENGTH_LONG).show();
            }
        });

    }
}
