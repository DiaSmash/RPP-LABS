package com.example.lab3_v1.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.lab3_v1.DBProvider;
import com.example.lab3_v1.R;
import java.util.Random;


public class MainActivity extends Activity
{
    final String LOG_TAG = "myLogs";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBProvider dbProvider = DBProvider.getInstance(this);
        final SQLiteDatabase db = dbProvider.getDB();

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        final Context context = this;
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                Intent intent = new Intent(context, ListActivity.class);
                context.startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                ContentValues contentValues = new ContentValues();
                Random random = new Random();
                String[] studentFio = {"", "", ""};

                for (int j = 0; j < 3; ++j)
                {
                    int index = random.nextInt(9);
                    studentFio[j] = DBProvider.studentNames[index][j];
                }

                contentValues.put("full_name", studentFio[0] + ' ' +
                                  studentFio[1] + ' ' + studentFio[2]);

                db.insert("students", null, contentValues);
            }
        });

        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                Cursor cursor = db.rawQuery("select * from SQLITE_SEQUENCE", null);
                cursor.moveToFirst();
                long id = cursor.getInt(cursor.getColumnIndex("seq"));
                cursor.close();

                ContentValues cv = new ContentValues();
                cv.put("full_name", "Иванов Иван Иванович");

                db.update("students", cv, "id = ?",
                          new String[] { String.valueOf(id) });

            }
        });
    }
}