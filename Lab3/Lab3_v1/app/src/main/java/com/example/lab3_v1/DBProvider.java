package com.example.lab3_v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.Random;


public class DBProvider
{
    private final String LOG_TAG = "myLogs";
    private final String DB_NAME = "lab3";
    private final int DB_VERSION = 1;
    public static String[][] studentNames = {
            {"Иванов", "Иван", "Иванович"},
            {"Петров", "Пётр", "Петрович"},
            {"Алексеев", "Алексей", "Алексеевич"},
            {"Рамзанов", "Рамзан", "Рамзанович"},
            {"Андреев", "Андрей", "Андреевич"},
            {"Михайлов", "Михаил", "Михаилович"},
            {"Владимиров", "Владимир", "Владимирович"},
            {"Николаев", "Николай", "Николаевич"},
            {"Константинов", "Константин", "Константинович"},
            {"Викторов", "Виктор ", "Викторович"}
    };

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private static volatile DBProvider instance;

    public DBProvider(Context context)
    {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, " --- Lab3 db v." + db.getVersion() + " --- ");
    }

    public static DBProvider getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new DBProvider(context);
        }
        return instance;
    }

    public SQLiteDatabase getDB()
    {
        return db;
    }

    public void refresh()
    {
        db.delete("students", null, null);
        dbHelper.fillTable(db);
    }

    class DBHelper extends SQLiteOpenHelper
    {
        /** Class which works with database */

        private DBHelper(Context context)
        {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db)
        {
            Log.d(LOG_TAG, " --- onCreate database --- ");

            // create table of **students**
            db.execSQL("create table students (" +
                    "id integer  primary key autoincrement, " +
                    "full_name text, " +
                    "date_add timestamp default CURRENT_TIMESTAMP" +
                    ");");

            fillTable(db);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.d(LOG_TAG, " --- onUpgrade database from " + oldVersion
                  + " to " + newVersion + " version --- ");
        }

        private void fillTable(SQLiteDatabase db)
        {
            ContentValues contentValues = new ContentValues();
            Random random = new Random();

            for (int i = 0; i < 5; ++i)
            {
                contentValues.clear();
                String[] studentFio = {"", "", ""};

                for (int j = 0; j < 3; ++j)
                {
                    int index = random.nextInt(9);
                    studentFio[j] = studentNames[index][j];
                }

                contentValues.put("full_name", studentFio[0] + " "
                                  + studentFio[1] + " " + studentFio[2]);
                db.insert("students", null, contentValues);
            }
        }
    }
}
