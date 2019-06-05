package com.example.lab2.activities;

import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;

import com.example.lab2.DataHolder;
import com.example.lab2.R;
import com.example.lab2.TechnologyAdapter;


public class ListActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        TechnologyAdapter technologyAdapter = new TechnologyAdapter(ListActivity.this);
        recyclerView.setAdapter(technologyAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (Build.VERSION.SDK_INT < 21)
            {
                finishAffinity();
            }
            else
            {
                finishAndRemoveTask();
            }

            return false;
        }

        return super.onKeyDown(keyCode, event);
    }
}
