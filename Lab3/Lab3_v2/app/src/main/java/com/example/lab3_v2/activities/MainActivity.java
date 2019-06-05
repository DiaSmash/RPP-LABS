package com.example.lab3_v2.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.lab3_v2.DBProvider;
import com.example.lab3_v2.R;
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

        writeDatabase(db);

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

                contentValues.put("last_name", studentFio[0]);
                contentValues.put("first_name", studentFio[1]);
                contentValues.put("patronymic", studentFio[2]);

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
                cv.put("last_name", "Иванов");
                cv.put("first_name", "Иван");
                cv.put("patronymic", "Иванович");

                db.update("students", cv, "id = ?",
                          new String[] {String.valueOf(id)});

            }
        });
    }
    
    private void writeDatabase(SQLiteDatabase db)
    {
        Cursor cursor = db.rawQuery("select * from students", null);
        logCursor(cursor, "Table students");
        cursor.close();
    }

    void logCursor(Cursor c, String title)
    {
        if (c != null)
        {
            if (c.moveToFirst())
            {
                StringBuilder sb = new StringBuilder();
                do {
                    sb.setLength(0);
                    for (String cn : c.getColumnNames())
                    {
                        sb.append(cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                } while (c.moveToNext());
            }
        }
    }
}