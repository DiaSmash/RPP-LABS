package com.example.lab2.loaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import java.io.InputStream;

import com.example.lab2.DataHolder;


public class LoadImageTask extends AsyncTask<String, Void, Bitmap>
{
    private int index;
    private ImageView imageView;

    public LoadImageTask(int index, ImageView imageView)
    {
        super();
        this.index = index;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params)
    {
        String imageUrl = params[0];
        Bitmap icon;

        try
        {
            InputStream in = new java.net.URL(imageUrl).openStream();
            icon = BitmapFactory.decodeStream(in);
            return icon;
        }
        catch (Exception exception)
        {
            Log.e("Error", exception.getMessage());
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap image)
    {
        DataHolder.getInstance().setTechnologyImage(image, index, imageView);
    }
}
