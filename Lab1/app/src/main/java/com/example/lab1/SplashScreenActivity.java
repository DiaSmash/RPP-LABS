package com.example.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenListener
{
    private SleepTask sleepTask;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sleepTask = new SleepTask(this);
        sleepTask.execute();
    }

    @Override
    public void onSplashScreenEnd()
    {
        Intent i = new Intent(SplashScreenActivity.this, ListActivity.class);
        startActivity(i);
        sleepTask = null;
    }
}
