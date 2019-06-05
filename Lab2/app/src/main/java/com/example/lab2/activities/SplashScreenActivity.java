package com.example.lab2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lab2.R;
import com.example.lab2.loaders.LoadJsonTask;


public class SplashScreenActivity extends AppCompatActivity implements SplashScreenListener
{
    private LoadJsonTask sleepTask;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        String url = getString(R.string.json_url);

        sleepTask = new LoadJsonTask(this);
        sleepTask.execute(url);
    }

    @Override
    public void onSplashScreenEnd()
    {
        Intent i = new Intent(SplashScreenActivity.this, ListActivity.class);
        startActivity(i);
        sleepTask = null;
    }
}
