package com.example.lakshaysharma.thenamestat;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.IOException;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.data.SimpleDatabase;

public class MainActivity extends SimpleActivity {

    private final int MAX_YEAR = 2010;
    private final int MIN_YEAR = 1880;
    private final int MAX_RANK = 1000;
    private SQLiteDatabase db;

    EditText editText;
    Switch aSwitch;
    GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SimpleDatabase.with(this).executeSqlFile("babynames");

        editText = findViewById(R.id.text);
        aSwitch = findViewById(R.id.sex);

        graph = findViewById(R.id.graph);
        graph.setTitle("Statistics Of The Name Provided");
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(MIN_YEAR);
        graph.getViewport().setMaxX(MAX_YEAR);
        graph.getViewport().setMaxY(MAX_RANK);
        graph.getViewport().setMinY(0);
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);

    }


    public void searchStats(View view) {

        graph.removeAllSeries();

        String name = editText.getText().toString();
        String sex = aSwitch.isChecked() ? "M" : "F";

        db = openOrCreateDatabase("babynames", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT year, rank FROM ranks WHERE name = '" + name
                + "' AND sex = '" + sex + "' ORDER BY year", null);

        if(cursor.moveToFirst()){

            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

            do {
                int year = cursor.getInt(cursor.getColumnIndex("year"));
                int rank = cursor.getInt(cursor.getColumnIndex("rank"));
                series.appendData(new DataPoint(year, MAX_RANK - rank), false, 100);
            }while (cursor.moveToNext());
            cursor.close();
            graph.addSeries(series);

        }

    }
}
