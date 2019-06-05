package com.example.lab4;

import android.app.PendingIntent;
import android.appwidget.*;
import android.content.*;
import android.widget.RemoteViews;
import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class WidgetProvider extends AppWidgetProvider
{
    static void updateAppWidget(Context context, AppWidgetManager widgetManager, int appWidgetId)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SharedPreferences preferences = context.getSharedPreferences("userPrefs", Context.MODE_PRIVATE);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.choose_date_widget);
        String content = preferences.getString("date", context.getString(R.string.date_text_base));

        if (content != null && !content.equals(context.getString(R.string.date_text_base)))
        {
            Date today = new Date();
            Date targetDay;
            try
            {
                targetDay = simpleDateFormat.parse(content);
            }
            catch (ParseException e)
            {
                targetDay = new Date();
            }
            int days = (int)TimeUnit.DAYS.convert(targetDay.getTime() - today.getTime(),
                                                  TimeUnit.MILLISECONDS);
            views.setTextViewText(R.id.date_text, "remains " + days + " days");
        }
        else
        {
            views.setTextViewText(R.id.date_text, content);
        }

        Intent openMain = new Intent(context, MainActivity.class);
        openMain.putExtra("type", 1);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 55, openMain,
                                                                PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.date_text, pendingIntent);
        widgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        for (int appWidgetId : appWidgetIds)
        {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

}
