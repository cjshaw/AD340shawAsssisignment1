package com.example.shawasssisignment1;

import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class SecondActivityTest {
    @Rule
    public ActivityTestRule<SecondActivity> activityTestRule
            = new ActivityTestRule<SecondActivity>(SecondActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Intent testIntent = new Intent();
            testIntent.putExtra(Constants.KEY_NAME, "Clint Shaw");
            testIntent.putExtra(Constants.KEY_OCC, "test occupation");
            testIntent.putExtra(Constants.KEY_DESC, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Vestibulum eleifend odio volutpat nibh ultricies mattis. " +
                    "Sed lobortis mauris ac turpis egestas, ut consequat ligula hendrerit. " +
                    "Nullam tempus neque nec neque lacinia venenatis sit amet ornare dolor. " +
                    "Praesent suscipit convallis orci sit amet fermentum. Mauris porta enim vitae congue ultricies. " +
                    "Suspendisse elementum eleifend auctor. Sed commodo ante nec placerat aliquam.");
            testIntent.putExtra(Constants.KEY_AGE, 25);
            testIntent.putExtra(Constants.KEY_IMG, Uri.parse(("content://media/external/images/media/337663")));
            return testIntent;
        }
    };

    @Test
    public void setsRightMessageBasedOnIntentExtra() {
        onView(withId(R.id.userAgeName))
                .check(matches(withText("25,\t\tClint Shaw")));

        onView(withId(R.id.userOcc))
                .check(matches(withText("test occupation")));

        onView(withId(R.id.userDesc))
                .check(matches(withText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Vestibulum eleifend odio volutpat nibh ultricies mattis. " +
                        "Sed lobortis mauris ac turpis egestas, ut consequat ligula hendrerit. " +
                        "Nullam tempus neque nec neque lacinia venenatis sit amet ornare dolor. " +
                        "Praesent suscipit convallis orci sit amet fermentum. Mauris porta enim vitae congue ultricies. " +
                        "Suspendisse elementum eleifend auctor. Sed commodo ante nec placerat aliquam.")));

        onView(withId(R.id.profileImg))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testGoBackBtn() {
        onView(withId(R.id.backBtn))
                .perform(scrollTo(),click());

        onView(withId(R.id.occEdit))
                .check(matches(withText("")));

        onView(withId(R.id.nameEdit))
                .check(matches(withText("")));

        onView(withId(R.id.emailEdit))
                .check(matches(withText("")));

        onView(withId(R.id.descBox))
                .check(matches(withText("")));

        onView(withId(R.id.birthdayEdit))
                .check(matches(withHint("Enter your birthday…")));

    }

    @Test
    public void test_backButton() {
        Espresso.pressBack();

        onView(withId(R.id.occEdit))
                .check(matches(withText("")));

        onView(withId(R.id.nameEdit))
                .check(matches(withText("")));

        onView(withId(R.id.emailEdit))
                .check(matches(withText("")));

        onView(withId(R.id.descBox))
                .check(matches(withText("")));

        onView(withId(R.id.birthdayEdit))
                .check(matches(withHint("Enter your birthday…")));
    }
}
