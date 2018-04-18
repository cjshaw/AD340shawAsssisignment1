package com.example.shawasssisignment1;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.PickerActions;
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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
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
        onView(withId(android.R.id.button1)).perform(click());


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

    @Test
    public void testOnSubmitUsername() {
        onView(withId(R.id.usernameEdit))
                .perform(typeText(""));

        onView(withId(R.id.nameEdit))
                .perform(typeText("Clint Shaw"));

        onView(withId(R.id.emailEdit))
                .perform(typeText("test@test.com"));

        onView(withId(R.id.birthdayEdit))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 1, 1));
        onView(withId(android.R.id.button1)).perform(click());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.submitBtn))
                .perform(click());

        onView(withText(Constants.USERNAME_MSG))
                .check(matches(isDisplayed()));

            }

    @Test
    public void testOnSubmitName() {
        onView(withId(R.id.usernameEdit))
                .perform(typeText("username"));

        onView(withId(R.id.nameEdit))
                .perform(typeText(""));

        onView(withId(R.id.emailEdit))
                .perform(typeText("test@test.com"));

        onView(withId(R.id.birthdayEdit))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 1, 1));
        onView(withId(android.R.id.button1)).perform(click());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.submitBtn))
                .perform(click());

        onView(withText(Constants.NAME_MSG))
                .check(matches(isDisplayed()));

    }

    @Test
    public void testOnSubmitEmail() {
        onView(withId(R.id.usernameEdit))
                .perform(typeText("username"));

        onView(withId(R.id.nameEdit))
                .perform(typeText("Clint Shaw"));

        onView(withId(R.id.emailEdit))
                .perform(typeText("testtest.com"));

        onView(withId(R.id.birthdayEdit))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 1, 1));
        onView(withId(android.R.id.button1)).perform(click());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.submitBtn))
                .perform(click());

        onView(withText(Constants.EMAIL_MSG))
                .check(matches(isDisplayed()));

    }

    @Test
    public void testOnSubmitDOB() {
        onView(withId(R.id.usernameEdit))
                .perform(typeText("username"));

        onView(withId(R.id.nameEdit))
                .perform(typeText("Clint Shaw"));

        onView(withId(R.id.emailEdit))
                .perform(typeText("test@test.com"));

        onView(withId(R.id.birthdayEdit))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 4, 30));
        onView(withId(android.R.id.button1)).perform(click());

        Espresso.closeSoftKeyboard();;

        onView(withId(R.id.submitBtn))
                .perform(click());

        onView(withText(Constants.DOB_MSG))
                .check(matches(isDisplayed()));

    }

    @Test
    public void canGoToSecondActivity() {
        onView(withId(R.id.usernameEdit))
                .perform(typeText("BigBad666"));

        onView(withId(R.id.nameEdit))
                .perform(typeText("Clint Shaw"));

        onView(withId(R.id.emailEdit))
                .perform(typeText("test@test.com"));

        onView(withId(R.id.birthdayEdit))
                .perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 1, 1));
        onView(withId(android.R.id.button1)).perform(click());

        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_USERNAME, "BigBad666");

        activityTestRule.launchActivity(intent);

    }
}