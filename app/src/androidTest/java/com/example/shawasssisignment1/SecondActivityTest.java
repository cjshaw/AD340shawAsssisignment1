package com.example.shawasssisignment1;

import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.shawasssisignment1.entity.Settings;
import com.example.shawasssisignment1.model.Matches;

import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.shawasssisignment1.TestUtils.withRecyclerView;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringStartsWith.startsWith;
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
                .check(matches(withText("Application Settings")));
    }

    @Test
    public void testLikeBtn() {
        SecondActivity activity = activityTestRule.getActivity();

        //swipe to matches tab
        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

        //click like button
        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, TestUtils.clickChildViewWithId(R.id.like_button)));
//        //check toast text
//        onView(withText("You liked Cool Guy Mike")).
//                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
//                check(matches(isDisplayed()));

        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(1, TestUtils.clickChildViewWithId(R.id.like_button)));
//        onView(withText("You liked Mark the King")).
//                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
//                check(matches(isDisplayed()));

        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(2, TestUtils.clickChildViewWithId(R.id.like_button)));
//        onView(withText("You liked Overachiever Alex")).
//                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
//                check(matches(isDisplayed()));

        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(3, TestUtils.clickChildViewWithId(R.id.like_button)));
//        onView(withText("You liked Iceman Judah")).
//                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
//                check(matches(isDisplayed()));

        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(4, TestUtils.clickChildViewWithId(R.id.like_button)));
//        onView(withText("You liked Hayden the Wrestler")).
//                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
//                check(matches(isDisplayed()));

//        onView(withId(R.id.my_recycler_view)).perform(
//                RecyclerViewActions.actionOnItemAtPosition(5, TestUtils.clickChildViewWithId(R.id.like_button)));
//        onView(withText("You liked Money man Ben")).
//                inRoot(withDecorView(not(activity.getWindow().getDecorView()))).
//                check(matches(isDisplayed()));
    }

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
//
//        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(5));
//
//        onView(withRecyclerView(R.id.my_recycler_view)
//                .atPositionOnView(5, R.id.card_title))
//                .check(matches(withText("Money man Ben")));

    }

    @Test
    public void testBadAgeRange() {
        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

        onView(withId(R.id.settingsFrag)).check(matches(withText(containsString("Application Settings"))));

        onView(withId(R.id.timeReminderText)).check(matches(withText(containsString("Matches Time Reminder"))));

        onView(withId(R.id.maxDistanceText)).check(matches(withText(containsString("Max Distance in miles"))));

        onView(withId(R.id.genderText)).check(matches(withText(containsString("Gender"))));

        onView(withId(R.id.profilePrivacy)).check(matches(withText(containsString("Set profile to public:"))));

        onView(withId(R.id.minAgeText)).check(matches(withText(containsString("Minimum Age"))));

        onView(withId(R.id.maxAgeText)).check(matches(withText(containsString("Maximum Age"))));

        //onView(withId(R.id.timeReminder)).perform(click());

        //onView(withId(R.id.timeReminder)).check(matches(withSpinnerText(containsString("12:00AM - 1:00AM"))));

//        onView(withId(R.id.maxDistance)).perform(click());
//        onData(is(instanceOf(String.class))).atPosition(2).perform(click());
//        onView(withId(R.id.maxDistance)).check(matches(withSpinnerText(containsString("25"))));
//
//        onView(withId(R.id.gender)).perform(click());
//        onData(is(instanceOf(String.class))).atPosition(4).perform(click());
//        onView(withId(R.id.gender)).check(matches(withSpinnerText(containsString("Other"))));

        onView(withId(R.id.minAge))
                .perform(typeText("44"));

        onView(withId(R.id.maxAge))
                .perform(scrollTo(), typeText("30"));

        closeSoftKeyboard();

        onView(withId(R.id.applyBtn))
                .perform(scrollTo(), click());

        onView(withText(MyConstants.MINMAX_MSG))
                .check(matches(isDisplayed()))
                .inRoot(isDialog())
                .perform(click());
    }

    @Test
    public void testTooYoung() {
        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

//        onView(withId(R.id.timeReminder)).perform(click());
//        onData(hasToString(startsWith("4:00AM"))).perform(click());
//        onView(withId(R.id.timeReminder)).check(matches(withSpinnerText(containsString("4:00AM - 5:00AM"))));
//
//        onView(withId(R.id.maxDistance)).perform(click());
//        onData(hasToString(startsWith("25"))).perform(click());
//        onView(withId(R.id.maxDistance)).check(matches(withSpinnerText(containsString("25"))));
//
//        onView(withId(R.id.gender)).perform(click());
//        onData(hasToString(startsWith("Other"))).perform(click());
//        onView(withId(R.id.gender)).check(matches(withSpinnerText(containsString("Other"))));

        closeSoftKeyboard();

        onView(withId(R.id.minAge))
                .perform(typeText("11"));

        onView(withId(R.id.maxAge))
                .perform(scrollTo(), typeText("55"));

        onView(withId(R.id.applyBtn))
                .perform(scrollTo(), click());

        onView(withText(MyConstants.PEDO_MSG))
                .check(matches(isDisplayed()))
                .inRoot(isDialog())
                .perform(click());
    }


    @Test
    public void testDBApply() {
        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

//        onView(withId(R.id.timeReminder)).perform(click());
//        onData(hasToString(startsWith("4:00AM"))).perform(click());
//        onView(withId(R.id.timeReminder)).check(matches(withSpinnerText(containsString("4:00AM - 5:00AM"))));
//
//        onView(withId(R.id.maxDistance)).perform(click());
//        onData(hasToString(startsWith("25"))).perform(click());
//        onView(withId(R.id.maxDistance)).check(matches(withSpinnerText(containsString("25"))));
//
//        onView(withId(R.id.gender)).perform(click());
//        onData(hasToString(startsWith("Other"))).perform(click());
//        onView(withId(R.id.gender)).check(matches(withSpinnerText(containsString("Other"))));

        closeSoftKeyboard();

        onView(withId(R.id.minAge))
                .perform(typeText("22"));

        onView(withId(R.id.maxAge))
                .perform(scrollTo(), typeText("55"));

        closeSoftKeyboard();

        onView(withId(R.id.applyBtn))
                .perform(scrollTo(), click());
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
        Matches testMatches = new Matches("Clint", false, "www.getpicture.com", "69.9", "22.5");

        assertEquals("Clint", testMatches.getName());
        assertEquals(false, testMatches.isLiked());
        assertEquals("www.getpicture.com", testMatches.getImageUrl());
        assertEquals(0, testMatches.describeContents());
        assertEquals("69.9", testMatches.getLatitude());
        assertEquals("22.5", testMatches.getLongitude());
    }

    @Test
    public void testSettingsEntity() {
        Settings testSettings = new Settings("clint_app", "12:00AM - 1:00AM", "50",
                "female", false, 18, 55);

        assertEquals("clint_app", testSettings.getAppId());
        assertEquals(false, testSettings.isProfilePublic());
        assertEquals("12:00AM - 1:00AM", testSettings.getMatchTimeReminder());
        assertEquals("50", testSettings.getMaxDistance());
        assertEquals("female", testSettings.getGender());
        assertEquals(18, testSettings.getMinAge());
        assertEquals(55, testSettings.getMaxAge());
    }

    @Test
    public void testEmptySettingsEntity() {
        Settings testSettings = new Settings();

        testSettings.setMatchTimeReminder("2:00AM - 3:00AM");
        testSettings.setAppId("clint_app");
        testSettings.setProfilePublic(true);
        testSettings.setMinAge(25);
        testSettings.setMaxAge(69);
        testSettings.setMaxDistance("15");
        testSettings.setGender("male");

        assertEquals("clint_app", testSettings.getAppId());
        assertEquals(true, testSettings.isProfilePublic());
        assertEquals("2:00AM - 3:00AM", testSettings.getMatchTimeReminder());
        assertEquals("15", testSettings.getMaxDistance());
        assertEquals("male", testSettings.getGender());
        assertEquals(25, testSettings.getMinAge());
        assertEquals(69, testSettings.getMaxAge());
    }

    @Test
    public void testAdapterGetItemCountForNull() {
        List<Matches> testList = null;
        MatchesTabFragment.OnListFragmentInteractionListener testListener = null;
        MatchesRecyclerViewAdapter testAdapter = new MatchesRecyclerViewAdapter(testList, testListener, 0.0, 0.0);

        assertEquals(0, testAdapter.getItemCount());
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

//        onView(withId(R.id.my_recycler_view)).perform(scrollToPosition(5));
//
//        onView(withRecyclerView(R.id.my_recycler_view)
//                .atPositionOnView(5, R.id.card_image))
//                .check(matches(isDisplayed()));

    }

}



