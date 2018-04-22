package com.example.shawasssisignment1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView ageName;
    TextView job;
    TextView desc;
    Button  backBtn;
    ImageView profileImg;

    /**
     * onCreate function that displays message to user with information gathered from the form.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ageName = findViewById(R.id.userAgeName);
        job = findViewById(R.id.userOcc);
        desc = findViewById(R.id.userDesc);
        profileImg = findViewById(R.id.profileImg);
        backBtn = findViewById(R.id.backBtn);

        StringBuilder ageNameMsg = new StringBuilder("");
        StringBuilder occMsg = new StringBuilder("");
        StringBuilder descMsg = new StringBuilder("");
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        assert b != null;

        Uri img = null;
        if (b.containsKey(Constants.KEY_IMG)) {
            img = b.getParcelable(Constants.KEY_IMG);
        }
        profileImg.setImageURI(img);

        if (b.containsKey(Constants.KEY_AGE)) {
            int userAge = b.getInt(Constants.KEY_AGE);
            ageNameMsg.append(userAge).append(",\t\t");
        }

        if (b.containsKey(Constants.KEY_NAME)){
            String name = b.getString(Constants.KEY_NAME);
            ageNameMsg.append(name);
        }
        ageName.setText(ageNameMsg);

        if (b.containsKey(Constants.KEY_OCC)) {
            String occ = b.getString(Constants.KEY_OCC);
            occMsg.append(occ);
        }
        job.setText(occMsg);

        if (b.containsKey(Constants.KEY_DESC)){
                String desc = b.getString(Constants.KEY_DESC);
            descMsg.append(desc);
            }
        desc.setText(descMsg);


    }

    /**
     * goes back to MainActivity and clears the stack of activities
     * @param v
     */
    public void goBack(View v){
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
