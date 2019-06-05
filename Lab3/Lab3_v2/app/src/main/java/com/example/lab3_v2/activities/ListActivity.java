package com.example.lab3_v2.activities;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lab3_v2.CardAdapter;
import com.example.lab3_v2.DBProvider;
import com.example.lab3_v2.R;

import java.util.ArrayList;


public class ListActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DBProvider dbProvider = DBProvider.getInstance(this);
        SQLiteDatabase db = dbProvider.getDB();
        ArrayList<ArrayList<String>> students = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from students", null);
        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                StringBuilder stringBuilder = new StringBuilder();
                do {
                    stringBuilder.setLength(0);
                    ArrayList<String> columns = new ArrayList<>();
                    for (String columnName : cursor.getColumnNames())
                    {
                        String cellData = cursor.getString(cursor.getColumnIndex(columnName));
                        columns.add(cellData);
                        stringBuilder.append(cellData);
                    }
                    students.add(columns);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        CardAdapter adapter = new CardAdapter(students);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
