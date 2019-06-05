package com.example.lab2;
import android.graphics.Bitmap;


public class TechnologyData extends Object
{
    private static String imageFolder = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/";
    private String graphic;
    private String name;
    private String helptext;
    private Bitmap image;
    private boolean isImageLoaded;

    public TechnologyData(String graphic, String name, String helptext)
    {
        this.graphic = graphic;
        this.name = name;
        this.helptext = helptext;
        image = null;
    }

    public String getImagePath()
    {
        return imageFolder + graphic;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return helptext;
    }

    public boolean isImageLoaded()
    {
        return isImageLoaded;
    }

    public Bitmap getImage()
    {
        return image;
    }

    public void setImage(Bitmap bitmap)
    {
        this.image = bitmap;
        isImageLoaded = true;
    }
}
