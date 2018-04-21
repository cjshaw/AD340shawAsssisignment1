package com.example.shawasssisignment1;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.widget.Button;
import android.widget.DatePicker;
import android.app.Dialog;
import android.widget.TextView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static int month, day, year;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current date as the default date in the date picker
        final Calendar c = Calendar.getInstance();
        int cYear = c.get(Calendar.YEAR);
        int cMonth = c.get(Calendar.MONTH);
        int cDay = c.get(Calendar.DAY_OF_MONTH);

        //Create a new DatePickerDialog instance and return it

        return new DatePickerDialog(getActivity(), this, cYear, cMonth, cDay);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        Button btn = getActivity().findViewById(R.id.birthdayEdit);
        month++;

        setMonth(month);
        setDay(day);
        setYear(year);

        btn.setText(month+"/"+day+"/"+year);
    }

    public static void setMonth(int month) {
        DatePickerFragment.month = month;
    }

    public static void setDay(int day) {
        DatePickerFragment.day = day;
    }

    public static void setYear(int year) {
        DatePickerFragment.year = year;
    }

    public static int getMonth() {
        return month;
    }

    public static int getDay() {
        return day;
    }

    public static int getYear() {
        return year;
    }
}

