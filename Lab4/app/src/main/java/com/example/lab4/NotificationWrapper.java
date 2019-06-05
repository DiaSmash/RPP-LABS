package com.example.lab4;

import android.annotation.TargetApi;
import android.app.*;
import android.content.*;
import android.os.Build;
import android.support.v4.app.NotificationCompat;


public class NotificationWrapper extends ContextWrapper
{
    public static final String channelID = "channelId";
    public static final String channelName = "channelName";
    private NotificationManager manager;

    public NotificationWrapper(Context base)
    {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel()
    {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager()
    {
        if (manager == null)
        {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return manager;
    }

    public NotificationCompat.Builder getNotification()
    {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                                              .setContentTitle("Notify!")
                                              .setContentText("This is a great day for all of us")
                                              .setSmallIcon(R.drawable.ic_launcher_background);
    }
}