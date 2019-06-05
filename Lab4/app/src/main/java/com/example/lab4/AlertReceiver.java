package com.example.lab4;

import android.content.BroadcastReceiver;
import android.content.*;
import android.support.v4.app.NotificationCompat;


public class AlertReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        NotificationWrapper notificationWrapper = new NotificationWrapper(context);
        NotificationCompat.Builder builder = notificationWrapper.getNotification();
        notificationWrapper.getManager().notify(1, builder.build());
    }
}
