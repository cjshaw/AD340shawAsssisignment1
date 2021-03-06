package com.example.shawasssisignment1;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Matcher;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView header;
    private Button submitBtn;
    private EditText editName;
    private EditText editEmail;
    private EditText editOcc;
    private Button birthdayEdit;
    private Button savedImage;
    private ImageView imageview;
    private Uri selectedImage;
    private EditText description;
    private EditText occupation;


    /**
     * onCreate that finds all form elements by id
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        header = findViewById(R.id.header);
        editOcc = findViewById(R.id.occEdit);
        editName = findViewById(R.id.nameEdit);
        editEmail = findViewById(R.id.emailEdit);
        submitBtn = findViewById(R.id.submitBtn);
        birthdayEdit = findViewById(R.id.birthdayEdit);
        savedImage = findViewById(R.id.savedImage);
        imageview = findViewById(R.id.selectedImg);
        description = findViewById(R.id.descBox);

    }

    /**
     * creates datepicker object and gets new fragment from DatePickerFragment class
     *
     * @param v
     */
    public void onDateClicked(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    /**
     * submits view and bundles username for SecondActivity.
     *
     * @param v
     */
    public void onSubmit(View v) {
        //if all input is correct, submits view
        if (!editOcc.getText().toString().trim().isEmpty() && getAge() >= MyConstants.MIN_AGE
                && validate(editEmail.getText().toString()) && !editName.getText().toString().trim().isEmpty()
                && selectedImage != null) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(MyConstants.KEY_IMG, selectedImage);
            intent.putExtra(MyConstants.KEY_OCC, editOcc.getText().toString());
            intent.putExtra(MyConstants.KEY_AGE, getAge());
            intent.putExtra(MyConstants.KEY_DESC, description.getText().toString());
            intent.putExtra(MyConstants.KEY_NAME, editName.getText().toString());

            startActivity(intent);
            //checks for empty occupation field
        } else if (editOcc.getText().toString().trim().isEmpty()) {
            dialogueAlert(MyConstants.OCC_MSG);
            //checks for age
        } else if (getAge() < 18) {
            dialogueAlert(MyConstants.DOB_MSG);
            //uses regex to check for valid emails.
        } else if (!validate(editEmail.getText().toString())) {
            dialogueAlert(MyConstants.EMAIL_MSG);
            //checks for empty name
        } else if (editName.getText().toString().trim().isEmpty()) {
            dialogueAlert(MyConstants.NAME_MSG);
        } else if(selectedImage == null) {
            dialogueAlert(MyConstants.IMG_MSG);
        }
    }

    /**
     * creates alert dialogue
     *
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
     *
     * @return age
     */
    public int getAge() {
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - DatePickerFragment.getYear();

        if (DatePickerFragment.getMonth() > today.get(Calendar.MONTH) + 1) {
            age--;
        } else if (DatePickerFragment.getMonth() == today.get(Calendar.MONTH) + 1) {
            if (DatePickerFragment.getDay() > today.get(Calendar.DAY_OF_MONTH)) {
                age--;
            }
        }
        return age;
    }

    /**
     * validates email address based on Regex patter
     *
     * @param emailStr
     * @return boolean
     */
    public static boolean validate(String emailStr) {
        Matcher matcher = MyConstants.VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public void imagePick(View v) {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(pickPhoto, 1);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if(resultCode == RESULT_OK) {
            if (requestCode == 1) {
                selectedImage = imageReturnedIntent.getData();

                if (selectedImage != null) {
                    imageview.setImageURI(selectedImage);
                    imageview.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * restores text in edit fields when screen is rotated.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(MyConstants.KEY_BDAY_BTN_TXT)) {
            birthdayEdit.setText((String) savedInstanceState.get(MyConstants.KEY_BDAY_BTN_TXT));
        }

        if (savedInstanceState.containsKey(MyConstants.KEY_IMG)) {
            imageview.setVisibility(View.VISIBLE);
            selectedImage = Uri.parse(savedInstanceState.getString(MyConstants.KEY_IMG));
            imageview.setImageURI(selectedImage);

        }

    }

    /**
     * saves Views in edit fields for when screen gets rotated.
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MyConstants.KEY_BDAY_BTN_TXT, birthdayEdit.getText().toString());

        if(selectedImage != null) {
            outState.putString(MyConstants.KEY_IMG, selectedImage.toString());
        }

    }

}
