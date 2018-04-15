package com.example.shawasssisignment1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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


    }

    public void onSubmit(View v) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(Constants.KEY_NAME, editName.getText().toString());
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
