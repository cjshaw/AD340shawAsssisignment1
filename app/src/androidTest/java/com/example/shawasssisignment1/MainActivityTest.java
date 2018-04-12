package com.example.shawasssisignment1;

import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
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
@RunWith(AndroidJUnit4.class)

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void text1() {
        onView(withId(R.id.myNameText))
                .check(matches(withText(R.string.myName)));
    }

    @Test
    public void text2() {
        onView(withId(R.id.touchCharlie))
                .check(matches(withText(R.string.touch_winchester)));
    }

    @Test
    public void onClick() {
        onView(withId(R.id.winCharlie))
                .perform(click());
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