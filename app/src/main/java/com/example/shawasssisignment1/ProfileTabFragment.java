package com.example.shawasssisignment1;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View profileView = inflater.inflate(R.layout.profile_fragment, container, false);

        ageName = profileView.findViewById(R.id.userAgeName);
        job = profileView.findViewById(R.id.userOcc);
        desc = profileView.findViewById(R.id.userDesc);
        profileImg = profileView.findViewById(R.id.profileImg);

        StringBuilder ageNameMsg = new StringBuilder("");
        StringBuilder occMsg = new StringBuilder("");
        StringBuilder descMsg = new StringBuilder("");

        Bundle arguments = getArguments();

        assert arguments != null;
        Uri img = arguments.getParcelable(Constants.KEY_IMG);
        profileImg.setImageURI(img);

        int userAge = arguments.getInt(Constants.KEY_AGE);
        ageNameMsg.append(userAge).append(",\t\t");

        String name = arguments.getString(Constants.KEY_NAME);
        ageNameMsg.append(name);
        ageName.setText(ageNameMsg);

        String occ = arguments.getString(Constants.KEY_OCC);
        occMsg.append(occ);
        job.setText(occMsg);

        String description = arguments.getString(Constants.KEY_DESC);
        descMsg.append(description);
        desc.setText(descMsg);

        return profileView;
    }
}
