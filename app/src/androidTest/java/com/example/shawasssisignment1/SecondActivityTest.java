package com.example.shawasssisignment1;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Parcel;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;

import com.example.shawasssisignment1.entity.Settings;
import com.example.shawasssisignment1.model.Matches;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.shawasssisignment1.TestUtils.withRecyclerView;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.anything;
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

    @Rule
    public GrantPermissionRule permissionRule1 = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);
    @Rule
    public GrantPermissionRule permissionRule2 = GrantPermissionRule.grant(Manifest.permission.ACCESS_COARSE_LOCATION);



    @Test
    public void setsRightMessageBasedOnIntentExtra() {
        MyConstants test = new MyConstants();
        AppDatabaseSingleton test2 = new AppDatabaseSingleton();

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
    public void testLikeBtn() throws InterruptedException {

        //swipe to matches tab
        onView(withText("Matches"))
                .perform(click());

        Thread.sleep(6000);
        //click like button
        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, TestUtils.clickChildViewWithId(R.id.like_button)));


    }

    @Test
    public void testNameOnCard() throws InterruptedException {

        Location fakeLocation = new Location(LocationManager.NETWORK_PROVIDER);
        fakeLocation.setLongitude(122.3321);
        fakeLocation.setLatitude(47.6062);

        //swipe to matches tab
        onView(withText("Matches"))
                .perform(click());

        Thread.sleep(6000);

        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(0, R.id.card_title))
                .check(matches(withText("Cool Guy Mike")));


    }

    @Test
    public void testBadAgeRange() {
        onView(withText("Settings"))
                .perform(click());

        onView(withId(R.id.settingsFrag)).check(matches(withText(containsString("Application Settings"))));

        onView(withId(R.id.timeReminderText)).check(matches(withText(containsString("Matches Time Reminder"))));

        onView(withId(R.id.maxDistanceText)).check(matches(withText(containsString("Max Distance in miles"))));

        onView(withId(R.id.genderText)).check(matches(withText(containsString("Gender"))));

        onView(withId(R.id.profilePrivacy)).check(matches(withText(containsString("Set profile to public:"))));

        onView(withId(R.id.minAgeText)).check(matches(withText(containsString("Minimum Age"))));

        onView(withId(R.id.maxAgeText)).check(matches(withText(containsString("Maximum Age"))));

        onView(withId(R.id.minAge))
                .perform(scrollTo(), replaceText(""))
                .perform(typeText("44"))
                .perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.maxAge))
                .perform(scrollTo(), replaceText(""))
                .perform(typeText("30"))
                .perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.applyBtn))
                .perform(scrollTo(), click());

        onView(withText(MyConstants.MINMAX_MSG))
                .check(matches(isDisplayed()))
                .inRoot(isDialog())
                .perform(click());
    }

    @Test
    public void testTooYoung() {
        onView(withText("Settings"))
                .perform(click());

        onView(withId(R.id.timeReminder)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.timeReminder)).check(matches(withSpinnerText(containsString("1:00AM"))));

        onView(withId(R.id.maxDistance)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.maxDistance)).check(matches(withSpinnerText(containsString("15"))));

        onView(withId(R.id.gender)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.gender)).check(matches(withSpinnerText(containsString("Female"))));

        closeSoftKeyboard();

        onView(withId(R.id.minAge))
                .perform(scrollTo(), replaceText(""))
                .perform(typeText("11"))
                .perform(ViewActions.closeSoftKeyboard());


        onView(withId(R.id.maxAge))
                .perform(scrollTo(), replaceText(""))
                .perform(typeText("55"))
                .perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.applyBtn))
                .perform(scrollTo(), click());

        onView(withText(MyConstants.PEDO_MSG))
                .check(matches(isDisplayed()));
        onView(withText("OK")).perform(click());
    }


    @Test
    public void testDBApply() {
        onView(withText("Settings"))
                .perform(click());

        closeSoftKeyboard();

        onView(withId(R.id.minAge))
                .perform(scrollTo(), replaceText(""))
                .perform(typeText("22"))
                .perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.maxAge))
                .perform(scrollTo(), replaceText(""))
                .perform(typeText("55"))
                .perform(ViewActions.closeSoftKeyboard());


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

        testMatches.writeToParcel(Parcel.obtain(), 1);
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

}



