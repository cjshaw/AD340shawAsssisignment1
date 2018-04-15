package com.example.shawasssisignment1;

import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.name);

        StringBuilder msg = new StringBuilder("Hello \n");
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b.containsKey(Constants.KEY_NAME)) {
            String name = b.getString(Constants.KEY_NAME);
            msg.append(name).append("\n");

        }

        textView.setText(msg);
    }
}
