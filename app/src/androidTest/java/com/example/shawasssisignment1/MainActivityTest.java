package com.example.shawasssisignment1;

import static org.junit.Assert.*;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;


public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void text() {
        onView(withId(R.id.myNameText))
                .check(matches(withText(R.string.myName)));

//        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
//
//        onView(withText(R.string.say_bye))
//                .perform(click());
//
//        onView(withId(R.id.hello_world_text))
//                .check(matches(withText(R.string.say_bye)));
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void onCreate() {
    }
}