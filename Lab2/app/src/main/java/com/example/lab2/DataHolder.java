package com.example.lab2;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.example.lab2.loaders.LoadImageTask;


public class DataHolder
{
    private static DataHolder holder = null;
    private ArrayList<TechnologyData> technologies;

    private DataHolder()
    {
        technologies = new ArrayList<>();
        System.out.println("Technologies count: " + technologies.size());
    }

    public static DataHolder getInstance()
    {
        if (holder == null)
        {
            holder = new DataHolder();
        }

        return holder;
    }

    public void loadTechnologyImage(int index, ImageView imageView)
    {
        if (index < technologies.size())
        {
            TechnologyData techData = technologies.get(index);

            if (!techData.isImageLoaded())
            {
                LoadImageTask loadImageTask = new LoadImageTask(index, imageView);
                loadImageTask.execute(technologies.get(index).getImagePath());
            }
            else if (techData.getImage() != null)
            {
                imageView.setImageBitmap(technologies.get(index).getImage());
            }
            else
            {
                imageView.setImageResource(R.mipmap.empty_image);
            }

        }
    }

    public TechnologyData getTechnology(int index)
    {
        if (index < technologies.size())
        {
            return technologies.get(index);
        }

        return null;
    }

    public int getTechologiesSize()
    {
        //System.out.println("Technologies count: " + technologies.size());
        return technologies.size();
    }

    public void setJson(String json)
    {
        Gson gson = new Gson();
        Type techType = new TypeToken<ArrayList<TechnologyData>>(){}.getType();

        technologies = gson.fromJson(json, techType);
        technologies.remove(0);
    }

    public void setTechnologyImage(Bitmap image, int index, ImageView imageView)
    {
        technologies.get(index).setImage(image);

        if (image != null)
        {
            imageView.setImageBitmap(technologies.get(index).getImage());
        }
        else
        {
            imageView.setImageResource(R.mipmap.empty_image);
        }
    }
}
