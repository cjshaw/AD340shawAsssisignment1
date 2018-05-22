package com.example.shawasssisignment1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.shawasssisignment1.entity.Settings;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SettingTabFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    TextView tabTitle;
    TextView timeReminderText;
    Spinner timeReminder;
    TextView maxDistText;
    Spinner maxDistance;
    TextView genderText;
    Spinner gender;
    Switch profilePrivacy;
    TextView minAgeText;
    TextView maxAgeText;
    EditText minAge;
    EditText maxAge;
    Button apply;

    String genderResult;
    String maxDistResult;
    String timeReminderResult;

    ArrayAdapter<CharSequence> timeReminderAdapter;
    ArrayAdapter<CharSequence> distanceAdapter;
    ArrayAdapter<CharSequence> genderAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        tabTitle = view.findViewById(R.id.settingsFrag);
        timeReminderText = view.findViewById(R.id.timeReminderText);
        timeReminder = view.findViewById(R.id.timeReminder);
        maxDistText = view.findViewById(R.id.maxDistanceText);
        maxDistance = view.findViewById(R.id.maxDistance);
        genderText = view.findViewById(R.id.genderText);
        gender = view.findViewById(R.id.gender);
        profilePrivacy = view.findViewById(R.id.profilePrivacy);
        minAgeText = view.findViewById(R.id.minAgeText);
        minAge = view.findViewById(R.id.minAge);
        maxAgeText = view.findViewById(R.id.maxAgeText);
        maxAge = view.findViewById(R.id.maxAge);
        apply = view.findViewById(R.id.applyBtn);

        new GetSettingsTask(getActivity(), this, MyConstants.DB_PRIMARYKEY).execute();

        genderAdapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()),
                R.array.gender, android.R.layout.simple_spinner_dropdown_item);
// Specify the layout to use when the list of choices appears
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        gender.setAdapter(genderAdapter);

        distanceAdapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()),
                R.array.max_distance, android.R.layout.simple_spinner_dropdown_item);
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maxDistance.setAdapter(distanceAdapter);

        timeReminderAdapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()),
                R.array.time_reminder, android.R.layout.simple_spinner_dropdown_item);
        timeReminderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeReminder.setAdapter(timeReminderAdapter);

        apply.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Settings settings = new Settings();

                if (!minAge.getText().toString().isEmpty()) {
                    String minAgeTemp = minAge.getText().toString(); //gets text from field
                    int finalMinAge = Integer.parseInt(minAgeTemp); //changes string number to int number
                    settings.setMinAge(finalMinAge);// sets value in setting entity
                }

                if (!maxAge.getText().toString().isEmpty()) {
                    String maxAgeTemp = maxAge.getText().toString();
                    int finalMaxAge = Integer.parseInt(maxAgeTemp);
                    settings.setMaxAge(finalMaxAge);
                }

                if (profilePrivacy.isChecked()) {
                    Boolean profilePrivateTemp = profilePrivacy.isChecked();
                    settings.setProfilePublic(profilePrivateTemp);
                } else {
                    settings.setProfilePublic(false);
                }

                gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //TODO fix spinner nonsense
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        genderResult = gender.getSelectedItem().toString();
                        settings.setGender(genderResult);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                maxDistance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//TODO fix spinner nonsense
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        maxDistResult = maxDistance.getSelectedItem().toString();
                        settings.setMaxDistance(maxDistResult);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                timeReminder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//TODO fix spinner nonsense
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        timeReminderResult = timeReminder.getSelectedItem().toString();
                        settings.setMaxDistance(timeReminderResult);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                Log.v("settingsObject", settings.toString());

                new SetSettingsTask(getActivity(), settings).execute();
            }
        });




        return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void dialogueAlert(String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setMessage(message);
        builder1.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder1.show();
    }

    private static class SetSettingsTask extends AsyncTask<Void, Void, Settings> {

        private WeakReference<Activity> weakActivity;
        private Settings settings;


        public SetSettingsTask(Activity activity, Settings settings) {
            weakActivity = new WeakReference<>(activity);
            this.settings = settings;
        }

        @Override
        protected Settings doInBackground(Void... voids) {
            Activity activity = weakActivity.get();
            if (activity == null) {
                return null;
            }
            AppDatabase db = AppDatabaseSingleton.getDatabase(activity.getApplicationContext());
            db.settingsDao().insertAll(settings);

            return settings;
        }

    }

    private static class GetSettingsTask extends AsyncTask<Void, Void, Settings> {

        private WeakReference<Activity> weakActivity;
        private WeakReference<SettingTabFragment> weakFrag;
        private String appId;
        private SettingTabFragment fragment;

        public GetSettingsTask(Activity activity, SettingTabFragment fragment, String appId) {
            this.weakActivity = new WeakReference<>(activity);
            this.appId = appId;
            this.weakFrag = new WeakReference<>(fragment);
        }

        @Override
        protected Settings doInBackground(Void... voids) {
            Activity activity = weakActivity.get();
            if (activity == null) {
                return null;
            }

            AppDatabase db = AppDatabaseSingleton.getDatabase(activity.getApplicationContext());

            String[] appIds = {this.appId};
            List<Settings> settings = db.settingsDao().loadAllByIds(appIds);

            if (settings.size() <= 0 || settings.get(0) == null) {
                return null;
            }
            Log.v("ListSettings", settings.get(0).toString());
            return settings.get(0);
        }

        @Override
        protected void onPostExecute(Settings settings) {
            this.fragment = weakFrag.get();
            if (settings == null || fragment == null) {
                return;
            }

            String finalMinAge = Integer.toString(settings.getMinAge());
            fragment.minAge.setText(finalMinAge);

            String finalMaxAge = Integer.toString(settings.getMaxAge());
            fragment.maxAge.setText(finalMaxAge);

            String genderCompareVal = settings.getGender();
            if (genderCompareVal != null) {
                int spinnerPosition = fragment.genderAdapter.getPosition(genderCompareVal);
                fragment.gender.setSelection(spinnerPosition);
            }

            String timeCompareVal = settings.getMatchTimeReminder();
            if (timeCompareVal != null) {
                int spinnerPosition = fragment.timeReminderAdapter.getPosition(timeCompareVal);
                fragment.timeReminder.setSelection(spinnerPosition);
            }

            String maxDistCompareVal = settings.getMaxDistance();
            if (maxDistCompareVal != null) {
                int spinnerPosition = fragment.distanceAdapter.getPosition(maxDistCompareVal);
                fragment.timeReminder.setSelection(spinnerPosition);
            }

            if (settings.isProfilePublic()) {
                fragment.profilePrivacy.toggle();
            }
        }
    }
}
