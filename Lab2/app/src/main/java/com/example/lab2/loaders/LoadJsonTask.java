package com.example.lab2.loaders;

import android.os.AsyncTask;

import com.example.lab2.DataHolder;
import com.example.lab2.activities.SplashScreenListener;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class LoadJsonTask extends AsyncTask<String, Void, String>
{
    private SplashScreenListener listener;
    private OkHttpClient client = new OkHttpClient();

    public LoadJsonTask(SplashScreenListener listener)
    {
        super();
        DataHolder.getInstance();
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params)
    {
        Request.Builder builder = new Request.Builder();
        builder.url(String.valueOf(params[0]));
        Request request = builder.build();

        try
        {
            Response response = client.newCall(request).execute();
            return response.body().string();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String json)
    {
        DataHolder.getInstance().setJson(json);
        listener.onSplashScreenEnd();
    }

}
