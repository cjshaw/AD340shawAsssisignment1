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

public class MainActivity extends AppCompatActivity {

    private TextView header;
    private Button submitBtn;
    private TextView name;
    private EditText editName;
    private TextView email;
    private EditText editEmail;
    private TextView username;
    private EditText editUsername;
    private Button birthdayEdit;
    private TextView birthdayDate;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private Calendar myCal;

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
        birthdayEdit = findViewById(R.id.birthdayEdit);


    }


    public void getCalendar(View v) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);
        myCal = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(
                MainActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateSetListener, year, month, day);
        dialog.show();
        month = month + 1;

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                myCal.set(Calendar.YEAR, year);
                myCal.set(Calendar.MONTH, month);
                myCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String date = month + "/" + dayOfMonth + "/" + year;
                birthdayEdit.setText(date);
            }
        };

    }

    public void onSubmit(View v) {
        if (!editUsername.getText().toString().isEmpty() && getAge() > 18) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(Constants.KEY_USERNAME, editUsername.getText().toString());
            intent.putExtra(Constants.KEY_AGE, getAge());
            startActivity(intent);
        } else if (editUsername.getText().toString().isEmpty()){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setMessage("You must enter have a username is join this club.");
            builder1.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            builder1.show();
        } else if (getAge() < 18) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setMessage("You must 18 or older, kiddo.");
            builder1.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            builder1.show();
        }
    }

    public int getAge() {
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - this.myCal.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < myCal.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return age;
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
