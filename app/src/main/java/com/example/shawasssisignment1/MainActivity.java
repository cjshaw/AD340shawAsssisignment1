package com.example.shawasssisignment1;

import android.app.AlertDialog;
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



    /**
     * onCreate that finds all form elements by id
     * @param savedInstanceState
     */
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

    /**
     * creates datepicker object and gets new fragment from DatePickerFragment class
     * @param v
     */
    public void onDateClicked(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"datePicker");
    }

    /**
     * submits view and bundles username for SecondActivity.
     * @param v
     */
    public void onSubmit(View v) {
        //if all input is correct, submits view
        if (!editUsername.getText().toString().trim().isEmpty() && getAge() >= Constants.MIN_AGE
                && validate(editEmail.getText().toString()) && !editName.getText().toString().trim().isEmpty()) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(Constants.KEY_USERNAME, editUsername.getText().toString());
            startActivity(intent);
        //checks for empty username
        } else if (editUsername.getText().toString().trim().isEmpty()){
            dialogueAlert(Constants.USERNAME_MSG);
        //checks for age
        } else if (getAge() < 18) {
            dialogueAlert(Constants.DOB_MSG);
        //uses regex to check for valid emails.
        } else if(!validate(editEmail.getText().toString())) {
            dialogueAlert(Constants.EMAIL_MSG);
        //checks for empty name
        } else if (editName.getText().toString().trim().isEmpty()){
            dialogueAlert(Constants.NAME_MSG);
        }
    }

    /**
     * creates alert dialogue
     * @param message
     */
    public void dialogueAlert(String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setMessage(message);
        builder1.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder1.show();
    }

    /**
     * calculates age based on date picked
     * @return age
     */
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

    /**
     * validates email address based on Regex patter
     * @param emailStr
     * @return boolean
     */
    public static boolean validate(String emailStr) {
        Matcher matcher = Constants.VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    /**
     * restores text in edit fields when screen is rotated.
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constants.KEY_BDAY_BTN_TXT)) {
            birthdayEdit.setText((String) savedInstanceState.get(Constants.KEY_BDAY_BTN_TXT));
        }

        if (savedInstanceState.containsKey(Constants.KEY_BDAY_TEXTVIEW)) {
            birthdayDate.setText((String) savedInstanceState.get(Constants.KEY_BDAY_TEXTVIEW));
        }
    }

    /**
     * saves strings in edit fields for when screen gets rotated.
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.KEY_BDAY_BTN_TXT, birthdayEdit.getText().toString());
        outState.putString(Constants.KEY_BDAY_TEXTVIEW, birthdayDate.getText().toString());
    }
}
