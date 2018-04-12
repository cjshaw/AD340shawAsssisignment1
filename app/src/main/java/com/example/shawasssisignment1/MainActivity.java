package com.example.shawasssisignment1;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView myName;
    private TextView touchWin;
    private ImageButton winchesterCharlie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.myName = findViewById(R.id.myNameText);

        this.touchWin = findViewById(R.id.touchCharlie);

        final ImageButton button = findViewById(R.id.winCharlie);

        final MediaPlayer bang = MediaPlayer.create(this, R.raw.bang);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bang.start(); //plays sound
            }
        });
    }
}
