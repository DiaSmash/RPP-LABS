package com.example.lab4;

import android.app.*;
import android.appwidget.AppWidgetManager;
import android.content.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.text.*;
import java.util.*;


public class MainActivity extends AppCompatActivity
{
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    TextView date;
    SharedPreferences preferences;
    boolean openIntentDialog = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        date = findViewById(R.id.date_text);
        date.setOnClickListener(view -> openDateDialog());
    }

    public void setDate(String day)
    {
        date.setText(day);
        preferences.edit().putString("date", day).apply();
        updateWidget();
        startAlarm(day);
    }

    public void updateWidget()
    {
        Intent intent = new Intent(this, NotificationWrapper.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), NotificationWrapper.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(intent);
    }

    public void openDateDialog()
    {
        if (getSupportFragmentManager().findFragmentByTag("datePicker") == null)
        {
            ChooseDateDialog dialog = new ChooseDateDialog();
            dialog.show(getSupportFragmentManager(), "datePicker");
        }
    }

    private void startAlarm(String day)
    {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        Date date;
        try
        {
            date = dateFormat.parse(day);
        }
        catch (ParseException e)
        {
            date = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    @Override
    protected void onPostResume()
    {
        super.onPostResume();
        if (openIntentDialog) openDateDialog();
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        if (intent.getIntExtra("type", 0) == 1) openIntentDialog = true;
    }

}
