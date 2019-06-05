package com.example.lab4;

import android.app.*;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import java.text.SimpleDateFormat;
import java.util.*;


public class ChooseDateDialog extends DialogFragment
{
    public Calendar dateAndTime = Calendar.getInstance();
    public DatePickerDialog dialog;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        dateAndTime = Calendar.getInstance();

        if (savedInstanceState != null)
        {
            dateAndTime = (Calendar) savedInstanceState.getSerializable("CALENDAR_");
        }

        DatePickerDialog.OnDateSetListener listener = (DatePicker view, int year,
                                                       int monthOfYear, int dayOfMonth) ->
        {
            setDate();
            MainActivity activity = (MainActivity)getActivity();
            if (activity != null) activity.setDate(simpleDateFormat.format(dateAndTime.getTime()));
        };

        dialog = new DatePickerDialog(getActivity(), listener, dateAndTime.get(Calendar.YEAR),
                                      dateAndTime.get(Calendar.MONTH),
                                      dateAndTime.get(Calendar.DAY_OF_MONTH));

        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        return dialog;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        setDate();
        outState.putSerializable("CALENDAR_", dateAndTime);
        super.onSaveInstanceState(outState);
    }

    public void setDate()
    {
        dateAndTime.set(Calendar.YEAR, dialog.getDatePicker().getYear());
        dateAndTime.set(Calendar.MONTH, dialog.getDatePicker().getMonth());
        dateAndTime.set(Calendar.DAY_OF_MONTH, dialog.getDatePicker().getDayOfMonth());
    }
}
