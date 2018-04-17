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
import android.widget.EditText;
import android.widget.TextView;

import android.app.DialogFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


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

    public void onDateClicked(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"datePicker");
    }


    public void onSubmit(View v) {
        if (!editUsername.getText().toString().trim().isEmpty() && getAge() >= Constants.MIN_AGE
                && validate(editEmail.getText().toString()) && !editName.getText().toString().trim().isEmpty()) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(Constants.KEY_USERNAME, editUsername.getText().toString());
            startActivity(intent);

        } else if (editUsername.getText().toString().trim().isEmpty()){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setMessage("You must enter have a username to join this club.");
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

        } else if(!validate(editEmail.getText().toString())) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setMessage("Re-enter a valid email!");
            builder1.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            builder1.show();

        } else if (editName.getText().toString().trim().isEmpty()){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setMessage("Please enter a name!");
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

        int age = today.get(Calendar.YEAR) - DatePickerFragment.getYear();

        if (DatePickerFragment.getMonth()>today.get(Calendar.MONTH)+1){
            age--;
        } else if (DatePickerFragment.getMonth()==today.get(Calendar.MONTH)+1){
            if(DatePickerFragment.getDay()>today.get(Calendar.DAY_OF_MONTH)) {
                age--;
            }
        }
        return age;
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constants.KEY_NAME)) {
            editName.setText((String)savedInstanceState.get(Constants.KEY_NAME));
        }

        if (savedInstanceState.containsKey(Constants.KEY_USERNAME)) {
            editUsername.setText((String)savedInstanceState.get(Constants.KEY_USERNAME));
        }

        if (savedInstanceState.containsKey(Constants.KEY_EMAIL)) {
            editEmail.setText((String)savedInstanceState.get(Constants.KEY_EMAIL));
        }

        if (savedInstanceState.containsKey(Constants.KEY_BUTTON_TXT)) {
            submitBtn.setText((String) savedInstanceState.get(Constants.KEY_BUTTON_TXT));
        }

        if (savedInstanceState.containsKey(Constants.KEY_BDAY_BTN_TXT)) {
            birthdayEdit.setText((String) savedInstanceState.get(Constants.KEY_BDAY_BTN_TXT));
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.KEY_NAME, editName.getText().toString());
        outState.putString(Constants.KEY_BUTTON_TXT, submitBtn.getText().toString());
        outState.putString(Constants.KEY_BDAY_BTN_TXT, birthdayEdit.getText().toString());
        outState.putString(Constants.KEY_USERNAME, editUsername.getText().toString());
        outState.putString(Constants.KEY_EMAIL, editEmail.getText().toString());
    }
}
