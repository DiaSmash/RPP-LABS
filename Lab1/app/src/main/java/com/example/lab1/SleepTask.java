package com.example.lab1;

import android.os.AsyncTask;


class SleepTask extends AsyncTask<Void, Void, Void>
{
    private SplashScreenListener listener;

    SleepTask(SplashScreenListener listener)
    {
        super();
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... params)
    {
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        if (listener != null)
            listener.onSplashScreenEnd();
    }
}
