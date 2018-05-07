package com.example.shawasssisignment1;

import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.shawasssisignment1.TestUtils.withRecyclerView;
import static org.hamcrest.Matchers.not;

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
    public void testBackButton() {
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


    @Test
    public void checkTabLayoutDisplayed() {
        onView(withId(R.id.viewpager))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    @Test
    public void swipeThroughTabsTest() {
        //Profile tab
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

        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

        //Settings tab
        onView(withId(R.id.settingsFrag))
                .check(matches(withText("This is the settings tab!")));
    }

    @Test
    public void testLikeToast() {
        SecondActivity activity = activityTestRule.getActivity();

        //swipe to matches tab
        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

        //click like button
        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, TestUtils.clickChildViewWithId(R.id.like_button)));
        //check toast text
        onView(withText("You liked James Howlett")).
                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
                check(matches(isDisplayed()));

        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(1, TestUtils.clickChildViewWithId(R.id.like_button)));
        onView(withText("You liked Jean Grey")).
                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
                check(matches(isDisplayed()));

        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(2, TestUtils.clickChildViewWithId(R.id.like_button)));
        onView(withText("You liked Scott Summers")).
                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
                check(matches(isDisplayed()));

        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(3, TestUtils.clickChildViewWithId(R.id.like_button)));
        onView(withText("You liked Jubilation Lee")).
                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
                check(matches(isDisplayed()));

        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(4, TestUtils.clickChildViewWithId(R.id.like_button)));
        onView(withText("You liked Piotr Nikolaievitch")).
                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
                check(matches(isDisplayed()));
    }

    @Test
    public void testNameOnCard() {

        //swipe to matches tab
        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(0, R.id.card_title))
                .check(matches(withText("James Howlett")));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(1));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(1, R.id.card_title))
                .check(matches(withText("Jean Grey")));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(2));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(2, R.id.card_title))
                .check(matches(withText("Scott Summers")));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(3));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(3, R.id.card_title))
                .check(matches(withText("Jubilation Lee")));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(4));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(4, R.id.card_title))
                .check(matches(withText("Piotr Nikolaievitch")));

    }

    @Test
    public void testDescOnCard() {

        //swipe to matches tab
        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(0, R.id.card_text))
                .check(matches(withText("He's the best and what he does. But what he does isn't very nice.")));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(1));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(1, R.id.card_text))
                .check(matches(withText("Jean Grey is a member of a subspecies of humans known as mutants, who are born with superhuman abilities. She was born with telepathic and telekinetic powers.")));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(2));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(2, R.id.card_text))
                .check(matches(withText("Cyclops is a member of a subspecies of humans known as mutants, who are born with superhuman abilities. Cyclops can emit powerful beams of energy from his eyes.")));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(3));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(3, R.id.card_text))
                .check(matches(withText("Jubilee is a member of the human subspecies known as mutants, born with superhuman abilities. She can generate pyrotechnic energy blasts from her hands.")));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(4));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(4, R.id.card_text))
                .check(matches(withText("A Russian mutant, he is a member of the X-Men. Colossus is able to transform himself into metallic form, making him the physically strongest of the team.")));

    }

    @Test
    public void testCardImage() {

        //swipe to matches tab
        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(0, R.id.card_image))
                .check(matches(isDisplayed()));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(1));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(1, R.id.card_image))
                .check(matches(isDisplayed()));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(2));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(2, R.id.card_image))
                .check(matches(isDisplayed()));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(3));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(3, R.id.card_image))
                .check(matches(isDisplayed()));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(4));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(4, R.id.card_image))
                .check(matches(isDisplayed()));

    }

}



