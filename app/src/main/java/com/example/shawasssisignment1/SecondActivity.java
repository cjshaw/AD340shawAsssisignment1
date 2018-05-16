package com.example.shawasssisignment1;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.shawasssisignment1.model.Matches;
import com.example.shawasssisignment1.viewmodels.MatchesViewModel;

public class SecondActivity extends AppCompatActivity implements MatchesTabFragment.OnListFragmentInteractionListener {

    private static final String TAG = "SecondActivity";

    private SectionPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private MatchesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        viewModel = new MatchesViewModel();
        mSectionsPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        // Setting ViewPager for each Tabs
        mViewPager = findViewById(R.id.viewpager);
        setupViewPager(mViewPager);

        // Set Tabs inside Toolbar
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);
    }


    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProfileTabFragment(), "Profile", getIntent().getExtras());
        adapter.addFragment(new MatchesTabFragment(), "Matches");
        adapter.addFragment(new SettingTabFragment(), "Settings");
        viewPager.setAdapter(adapter);
    }

    /**
     * Clears form content when android OS back button is pressed
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onListFragmentInteraction(Matches item) {
        item.liked = !item.liked;
        Context context = getApplicationContext();
        Toast.makeText(context, "You liked " + item.name, Toast.LENGTH_LONG).show();
        viewModel.updateMatchesItem(item);
    }

    @Override
    protected void onPause() {
        viewModel.clear();
        super.onPause();
    }
}
