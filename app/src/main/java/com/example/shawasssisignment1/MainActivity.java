package com.example.shawasssisignment1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView header;
    private Button submitBtn;
    private TextView name;
    private EditText editName;
    private TextView email;
    private EditText editEmail;
    private TextView username;
    private EditText editUsername;
    private TextView birthdayDate;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private int month;
    private int day;
    private int year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header = findViewById(R.id.header);
        username = findViewById(R.id.username);
        editUsername = findViewById(R.id.usernameEdit);
        name = findViewById(R.id.name);
        editName = findViewById(R.id.nameEdit);
        email = findViewById(R.id.email);
        editEmail = findViewById(R.id.emailEdit);
        submitBtn = findViewById(R.id.submitBtn);
        birthdayDate = findViewById(R.id.birthday);


    }


    public int getCalendar(View v) {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog dialog = new DatePickerDialog(
                MainActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateSetListener, year, month, day);
        dialog.getWindow();
        dialog.show();
        month = month +1;

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String date = month + "/" + dayOfMonth + "/" + year;
                birthdayDate.setText(date);
            }
        };
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int age = 0;
        if(currentYear - year >= 18 && currentMonth >= month && currentDay >= day){
            age = currentYear - year;
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
// Add the buttons
            builder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });
// Set other dialog properties

// Create the AlertDialog
            AlertDialog alertDialog = builder.create();
        }
        return age;
    }

    public void onSubmit(View v) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(Constants.KEY_NAME, editName.getText().toString());
        intent.putExtra(Constants.KEY_EMAIL, editEmail.getText().toString());
        intent.putExtra(Constants.KEY_USERNAME, editUsername.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constants.KEY_NAME)) {
            name.setText((String)savedInstanceState.get(Constants.KEY_NAME));
        }

        if (savedInstanceState.containsKey(Constants.KEY_BUTTON_TXT)) {
            submitBtn.setText((String) savedInstanceState.get(Constants.KEY_BUTTON_TXT));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.KEY_NAME, name.getText().toString());
        outState.putString(Constants.KEY_BUTTON_TXT, submitBtn.getText().toString());
    }
}
