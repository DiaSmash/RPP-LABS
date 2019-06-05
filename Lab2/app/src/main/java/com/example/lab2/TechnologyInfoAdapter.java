package com.example.lab2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class TechnologyInfoAdapter extends FragmentPagerAdapter
{
    public TechnologyInfoAdapter(FragmentManager manager)
    {
        super(manager);
    }

    @Override
    public int getCount()
    {
        return(DataHolder.getInstance().getTechologiesSize());
    }

    @Override
    public TechnologyInfoFragment getItem(int index)
    {
        return(TechnologyInfoFragment.newInstance(index));
    }
}
