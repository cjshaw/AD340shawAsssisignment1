package com.example.shawasssisignment1;

import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void canSubmitWithRotate() {
        onView(withId(R.id.usernameEdit))
                .perform(typeText("username"));

        onView(withId(R.id.nameEdit))
                .perform(typeText("Clint Shaw"));

        onView(withId(R.id.emailEdit))
                .perform(typeText("test@test.com"));

        onView(withId(R.id.birthdayEdit))
                .perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 1, 1));

        onView(withId(R.id.birthdayEdit))
                .check(matches(withText("1/1/2000")));

        TestUtils.rotateScreen(activityTestRule.getActivity());

        onView(withId(R.id.usernameEdit))
                .check(matches(withText("username")));

        onView(withId(R.id.nameEdit))
                .check(matches(withText("Clint Shaw")));

        onView(withId(R.id.emailEdit))
                .check(matches(withText("test@test.com")));

        onView(withId(R.id.birthdayEdit))
                .check(matches(withText("1/1/2000")));
    }

//    @Test
//    public void testOnSubmit() {
//
//    }

    @Test
    public void canGoToSecondActivityWithMessage() {
        onView(withId(R.id.usernameEdit))
                .perform(typeText("BigBad666"));

        onView(withId(R.id.usernameEdit))
                .perform(typeText("username"));

        onView(withId(R.id.nameEdit))
                .perform(typeText("Clint Shaw"));

        onView(withId(R.id.emailEdit))
                .perform(typeText("test@test.com"));

        onView(withId(R.id.birthdayEdit))
                .perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 1, 1));

        Intents.init();
        onView(withId(R.id.submitBtn)).perform(click());
        intended(hasComponent(SecondActivity.class.getName()));
        intended(hasExtra(Constants.KEY_NAME, "BigBad666"));
        Intents.release();
    }
}