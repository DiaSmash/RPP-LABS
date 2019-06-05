package com.example.lab2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class TechnologyInfoFragment extends Fragment
{
    public TechnologyInfoFragment() { }

    public static TechnologyInfoFragment newInstance(int index)
    {
        TechnologyInfoFragment fragment = new TechnologyInfoFragment();

        Bundle args=new Bundle();
        args.putInt("index", index);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.technology_info,
                                                     container, false);

        try
        {
            assert getArguments() != null;

            if(getArguments().getInt("index", -1) >= 0)
            {
                int index = getArguments().getInt("index");

                TextView nameView = view.findViewById(R.id.name);
                TextView descView = view.findViewById(R.id.description);
                ImageView iconView = view.findViewById(R.id.icon);

                nameView.setText(DataHolder.getInstance().getTechnology(index).getName());
                descView.setText(DataHolder.getInstance().getTechnology(index).getDescription());
                DataHolder.getInstance().loadTechnologyImage(index, iconView);
            }
        }
        catch(Exception exception)
        {
            //...
        }

        return view;
    }
}
