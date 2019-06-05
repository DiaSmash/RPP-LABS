package com.example.lab2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


public class TechnologyInfoActivity extends FragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.technology_info_pager);

        // Instantiate a ViewPager and a PagerAdapter.
        ViewPager pager=findViewById(R.id.pager);

        pager.setAdapter(new TechnologyInfoAdapter(getSupportFragmentManager()));
        pager.setCurrentItem((int)getIntent().getSerializableExtra("index"));
    }
}
