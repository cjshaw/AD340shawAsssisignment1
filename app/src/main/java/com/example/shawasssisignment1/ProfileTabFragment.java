package com.example.shawasssisignment1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class ProfileTabFragment extends Fragment {

    TextView ageName;
    TextView job;
    TextView desc;
    ImageView profileImg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View profileView = inflater.inflate(R.layout.profile_fragment, container, false);

        ageName = profileView.findViewById(R.id.userAgeName);
        job = profileView.findViewById(R.id.userOcc);
        desc = profileView.findViewById(R.id.userDesc);
        profileImg = profileView.findViewById(R.id.profileImg);

        StringBuilder ageNameMsg = new StringBuilder("");
        StringBuilder occMsg = new StringBuilder("");
        StringBuilder descMsg = new StringBuilder("");
        Intent intent = getActivity().getIntent();
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

        return profileView;
    }
}
