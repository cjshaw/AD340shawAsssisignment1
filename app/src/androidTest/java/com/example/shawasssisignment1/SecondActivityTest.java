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

import com.example.shawasssisignment1.model.Matches;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

public class SecondActivityTest {

    @Rule
    public ActivityTestRule<SecondActivity> activityTestRule
            = new ActivityTestRule<SecondActivity>(SecondActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Intent testIntent = new Intent();
            testIntent.putExtra(MyConstants.KEY_NAME, "Clint Shaw");
            testIntent.putExtra(MyConstants.KEY_OCC, "test occupation");
            testIntent.putExtra(MyConstants.KEY_DESC, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Vestibulum eleifend odio volutpat nibh ultricies mattis. " +
                    "Sed lobortis mauris ac turpis egestas, ut consequat ligula hendrerit. " +
                    "Nullam tempus neque nec neque lacinia venenatis sit amet ornare dolor. " +
                    "Praesent suscipit convallis orci sit amet fermentum. Mauris porta enim vitae congue ultricies. " +
                    "Suspendisse elementum eleifend auctor. Sed commodo ante nec placerat aliquam.");
            testIntent.putExtra(MyConstants.KEY_AGE, 25);
            testIntent.putExtra(MyConstants.KEY_IMG, Uri.parse(("content://media/external/images/media/337663")));
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

//    @Test
//    public void testLikeToast() {
//        SecondActivity activity = activityTestRule.getActivity();
//
//        //swipe to matches tab
//        onView(withId(R.id.viewpager))
//                .perform(swipeLeft());
//
//        //click like button
//        onView(withId(R.id.my_recycler_view)).perform(
//                RecyclerViewActions.actionOnItemAtPosition(0, TestUtils.clickChildViewWithId(R.id.like_button)));
//        //check toast text
//        onView(withText("You liked Cool Guy Mike")).
//                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
//                check(matches(isDisplayed()));
//
//        onView(withId(R.id.my_recycler_view)).perform(
//                RecyclerViewActions.actionOnItemAtPosition(1, TestUtils.clickChildViewWithId(R.id.like_button)));
//        onView(withText("You liked Mark the King")).
//                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
//                check(matches(isDisplayed()));
//
//        onView(withId(R.id.my_recycler_view)).perform(
//                RecyclerViewActions.actionOnItemAtPosition(2, TestUtils.clickChildViewWithId(R.id.like_button)));
//        onView(withText("You liked Overachiever Alex")).
//                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
//                check(matches(isDisplayed()));
//
//        onView(withId(R.id.my_recycler_view)).perform(
//                RecyclerViewActions.actionOnItemAtPosition(3, TestUtils.clickChildViewWithId(R.id.like_button)));
//        onView(withText("You liked Iceman Judah")).
//                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
//                check(matches(isDisplayed()));
//
//        onView(withId(R.id.my_recycler_view)).perform(
//                RecyclerViewActions.actionOnItemAtPosition(4, TestUtils.clickChildViewWithId(R.id.like_button)));
//        onView(withText("You liked Hayden the Wrestler")).
//                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
//                check(matches(isDisplayed()));
//
//        onView(withId(R.id.my_recycler_view)).perform(
//                RecyclerViewActions.actionOnItemAtPosition(5, TestUtils.clickChildViewWithId(R.id.like_button)));
//        onView(withText("You liked Money man Ben")).
//                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
//                check(matches(isDisplayed()));
//    }

    @Test
    public void testNameOnCard() {

        //swipe to matches tab
        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(0, R.id.card_title))
                .check(matches(withText("Cool Guy Mike")));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(1));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(1, R.id.card_title))
                .check(matches(withText("Mark the King")));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(2));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(2, R.id.card_title))
                .check(matches(withText("Overachiever Alex")));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(3));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(3, R.id.card_title))
                .check(matches(withText("Iceman Judah")));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(4));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(4, R.id.card_title))
                .check(matches(withText("Hayden the Wrestler")));

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(5));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(5, R.id.card_title))
                .check(matches(withText("Money man Ben")));

    }

    @Test
    public void testMyConstants() {
        assertEquals("name", MyConstants.KEY_NAME);
        assertEquals("bday_btn_txt", MyConstants.KEY_BDAY_BTN_TXT);
        assertEquals("profile_img", MyConstants.KEY_IMG);
        assertEquals("user_age", MyConstants.KEY_AGE);
        assertEquals("user_description", MyConstants.KEY_DESC);
        assertEquals("user_occupation", MyConstants.KEY_OCC);
        assertEquals("recycleview_state", MyConstants.KEY_RV_STATE);
        assertEquals("You gotta have a job to get a date.", MyConstants.OCC_MSG);
        assertEquals("You must 18 or older, kiddo.", MyConstants.DOB_MSG);
        assertEquals("Re-enter a valid email!", MyConstants.EMAIL_MSG);
        assertEquals("Please enter a name!", MyConstants.NAME_MSG);
        assertEquals("Your age is: ", MyConstants.AGE_MSG);
        assertEquals("Please select a photo", MyConstants.IMG_MSG);

    }

    @Test
    public void testMatchesModel() {
        Matches testMatches = new Matches("Clint", false, "www.getpicture.com");

        assertEquals("Clint", testMatches.getName());
        assertEquals(false, testMatches.isLiked());
        assertEquals("www.getpicture.com", testMatches.getImageUrl());
        assertEquals(0, testMatches.describeContents());
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

        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(5));

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(5, R.id.card_image))
                .check(matches(isDisplayed()));

    }

}



