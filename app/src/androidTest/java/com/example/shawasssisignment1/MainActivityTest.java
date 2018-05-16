package com.example.shawasssisignment1;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void canSubmitWithRotate() {

        onView(withId(R.id.nameEdit))
                .perform(typeText("Clint Shaw"));

        onView(withId(R.id.occEdit))
                .perform(typeText("Sandwich Artist at Subway"));

        onView(withId(R.id.emailEdit))
                .perform(typeText("test@test.com"));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.birthdayEdit))
                .perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 1, 1));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.birthdayEdit))
                .check(matches(withText("1/1/2000")));

        TestUtils.rotateScreen(activityTestRule.getActivity());

        onView(withId(R.id.nameEdit))
                .check(matches(withText("Clint Shaw")));

        onView(withId(R.id.occEdit))
                .check(matches(withText("Sandwich Artist at Subway")));

        onView(withId(R.id.emailEdit))
                .check(matches(withText("test@test.com")));

        onView(withId(R.id.birthdayEdit))
                .check(matches(withText("1/1/2000")));
    }

    @Test
    public void testOnSubmitOcc() {

        onView(withId(R.id.nameEdit))
                .perform(typeText("Clint Shaw"));

        onView(withId(R.id.emailEdit))
                .perform(typeText("test@test.com"));

        onView(withId(R.id.occEdit))
                .perform(typeText(""));

        onView(withId(R.id.descBox)).perform(typeText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Vestibulum eleifend odio volutpat nibh ultricies mattis. " +
                "Sed lobortis mauris ac turpis egestas, ut consequat ligula hendrerit. " +
                "Nullam tempus neque nec neque lacinia venenatis sit amet ornare dolor. " +
                "Praesent suscipit convallis orci sit amet fermentum. Mauris porta enim vitae congue ultricies. " +
                "Suspendisse elementum eleifend auctor. Sed commodo ante nec placerat aliquam."));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.birthdayEdit))
                .perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 1, 1));

        onView(withId(android.R.id.button1)).perform(click());

        Intent resultData = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        resultData.setData(Uri.parse(("content://media/external/images/media/337663")));
        Matcher<Intent> MediaPickIntent = allOf(hasAction(Intent.ACTION_PICK), hasData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
        Intents.init();
        intending(MediaPickIntent).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData));

        onView(withId(R.id.savedImage)).perform(click());
        intended(MediaPickIntent);
        Intents.release();

        onView(withId(R.id.submitBtn))
                .perform(scrollTo(), click());

        onView(withText(Constants.OCC_MSG))
                .check(matches(isDisplayed()))
                .inRoot(isDialog())
                .perform(click());
            }

    @Test
    public void testOnSubmitImg() {

        onView(withId(R.id.nameEdit))
                .perform(typeText("Clint Shaw"));

        onView(withId(R.id.emailEdit))
                .perform(typeText("test@test.com"));

        onView(withId(R.id.occEdit))
                .perform(typeText("test occupation"));

        onView(withId(R.id.descBox)).perform(typeText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Vestibulum eleifend odio volutpat nibh ultricies mattis. " +
                "Sed lobortis mauris ac turpis egestas, ut consequat ligula hendrerit. " +
                "Nullam tempus neque nec neque lacinia venenatis sit amet ornare dolor. " +
                "Praesent suscipit convallis orci sit amet fermentum. Mauris porta enim vitae congue ultricies. " +
                "Suspendisse elementum eleifend auctor. Sed commodo ante nec placerat aliquam."));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.birthdayEdit))
                .perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 1, 1));

        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.submitBtn))
                .perform(scrollTo(), click());

        onView(withText(Constants.IMG_MSG))
                .check(matches(isDisplayed()))
                .inRoot(isDialog())
                .perform(click());
    }

    @Test
    public void checkImgPick() {
        //to check view on screen

        Intent resultData = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        resultData.setData(Uri.parse(("content://media/external/images/media/337663")));
        Matcher<Intent> MediaPickIntent = allOf(hasAction(Intent.ACTION_PICK), hasData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
        Intents.init();
        intending(MediaPickIntent).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData));

        onView(withId(R.id.savedImage)).perform(click());
        intended(MediaPickIntent);
        Intents.release();

        onView(withId(R.id.selectedImg)).check(matches(isDisplayed()));

        //To check the image pick works or not
        try{
            Thread.sleep(4500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOnSubmitName() {
        onView(withId(R.id.nameEdit))
                .perform(typeText(""));

        onView(withId(R.id.emailEdit))
                .perform(typeText("test@test.com"));

        onView(withId(R.id.occEdit))
                .perform(typeText("test occupation"));

        onView(withId(R.id.descBox)).perform(typeText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Vestibulum eleifend odio volutpat nibh ultricies mattis. " +
                "Sed lobortis mauris ac turpis egestas, ut consequat ligula hendrerit. " +
                "Nullam tempus neque nec neque lacinia venenatis sit amet ornare dolor. " +
                "Praesent suscipit convallis orci sit amet fermentum. Mauris porta enim vitae congue ultricies. " +
                "Suspendisse elementum eleifend auctor. Sed commodo ante nec placerat aliquam."));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.birthdayEdit))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 1, 1));
        onView(withId(android.R.id.button1)).perform(click());

        Intent resultData = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        resultData.setData(Uri.parse(("content://media/external/images/media/337663")));
        Matcher<Intent> MediaPickIntent = allOf(hasAction(Intent.ACTION_PICK), hasData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
        Intents.init();
        intending(MediaPickIntent).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData));

        onView(withId(R.id.savedImage)).perform(click());
        intended(MediaPickIntent);
        Intents.release();

        onView(withId(R.id.submitBtn))
                .perform(scrollTo(), click());

        onView(withText(Constants.NAME_MSG))
                .check(matches(isDisplayed()))
                .inRoot(isDialog())
                .perform(click());

    }

    @Test
    public void testOnSubmitEmail() {
        onView(withId(R.id.nameEdit))
                .perform(typeText("Clint Shaw"));

        onView(withId(R.id.emailEdit))
                .perform(typeText("testtest.com"));

        onView(withId(R.id.occEdit))
                .perform(typeText("username"));

        onView(withId(R.id.descBox)).perform(typeText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Vestibulum eleifend odio volutpat nibh ultricies mattis. " +
                "Sed lobortis mauris ac turpis egestas, ut consequat ligula hendrerit. " +
                "Nullam tempus neque nec neque lacinia venenatis sit amet ornare dolor. " +
                "Praesent suscipit convallis orci sit amet fermentum. Mauris porta enim vitae congue ultricies. " +
                "Suspendisse elementum eleifend auctor. Sed commodo ante nec placerat aliquam."));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.birthdayEdit))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 1, 1));
        onView(withId(android.R.id.button1)).perform(click());

        Intent resultData = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        resultData.setData(Uri.parse(("content://media/external/images/media/337663")));
        Matcher<Intent> MediaPickIntent = allOf(hasAction(Intent.ACTION_PICK), hasData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
        Intents.init();
        intending(MediaPickIntent).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData));

        onView(withId(R.id.savedImage)).perform(click());
        intended(MediaPickIntent);
        Intents.release();

        onView(withId(R.id.submitBtn))
                .perform(scrollTo(), click());

        onView(withText(Constants.EMAIL_MSG))
                .check(matches(isDisplayed()))
                .inRoot(isDialog())
                .perform(click());

    }

    @Test
    public void testOnSubmitDOB() {
        onView(withId(R.id.nameEdit))
                .perform(typeText("Clint Shaw"));

        onView(withId(R.id.emailEdit))
                .perform(typeText("test@test.com"));

        onView(withId(R.id.occEdit))
                .perform(typeText("occupation test"));

        onView(withId(R.id.descBox)).perform(typeText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Vestibulum eleifend odio volutpat nibh ultricies mattis. " +
                "Sed lobortis mauris ac turpis egestas, ut consequat ligula hendrerit. " +
                "Nullam tempus neque nec neque lacinia venenatis sit amet ornare dolor. " +
                "Praesent suscipit convallis orci sit amet fermentum. Mauris porta enim vitae congue ultricies. " +
                "Suspendisse elementum eleifend auctor. Sed commodo ante nec placerat aliquam."));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.birthdayEdit))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2001, 4, 30));
        onView(withId(android.R.id.button1)).perform(click());

        Intent resultData = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        resultData.setData(Uri.parse(("content://media/external/images/media/337663")));
        Matcher<Intent> MediaPickIntent = allOf(hasAction(Intent.ACTION_PICK), hasData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
        Intents.init();
        intending(MediaPickIntent).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData));

        onView(withId(R.id.savedImage)).perform(click());
        intended(MediaPickIntent);
        Intents.release();

        onView(withId(R.id.submitBtn))
                .perform(scrollTo(), click());

        onView(withText(Constants.DOB_MSG))
                .check(matches(isDisplayed()))
                .inRoot(isDialog())
                .perform(click());

    }

    @Test
    public void canGoToSecondActivity() {

        onView(withId(R.id.nameEdit))
                .perform(typeText("Clint Shaw"));

        onView(withId(R.id.emailEdit))
                .perform(typeText("test@test.com"));

        onView(withId(R.id.occEdit))
                .perform(typeText("occupation test"));

        onView(withId(R.id.descBox)).perform(typeText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Vestibulum eleifend odio volutpat nibh ultricies mattis. " +
                "Sed lobortis mauris ac turpis egestas, ut consequat ligula hendrerit. " +
                "Nullam tempus neque nec neque lacinia venenatis sit amet ornare dolor. " +
                "Praesent suscipit convallis orci sit amet fermentum. Mauris porta enim vitae congue ultricies. " +
                "Suspendisse elementum eleifend auctor. Sed commodo ante nec placerat aliquam."));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.birthdayEdit))
                .perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 1, 1));
        onView(withId(android.R.id.button1)).perform(click());

        Intent resultData = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        resultData.setData(Uri.parse(("content://media/external/images/media/337663")));
        Matcher<Intent> MediaPickIntent = allOf(hasAction(Intent.ACTION_PICK), hasData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
        Intents.init();
        intending(MediaPickIntent).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData));

        onView(withId(R.id.savedImage)).perform(click());
        intended(MediaPickIntent);
        Intents.release();


        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_NAME, "Clint Shaw");
        intent.putExtra(Constants.KEY_OCC, "occupation test");
        intent.putExtra(Constants.KEY_AGE, 18);
        intent.putExtra(Constants.KEY_DESC, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Vestibulum eleifend odio volutpat nibh ultricies mattis. " +
                "Sed lobortis mauris ac turpis egestas, ut consequat ligula hendrerit. " +
                "Nullam tempus neque nec neque lacinia venenatis sit amet ornare dolor. " +
                "Praesent suscipit convallis orci sit amet fermentum. Mauris porta enim vitae congue ultricies. " +
                "Suspendisse elementum eleifend auctor. Sed commodo ante nec placerat aliquam.");
        intent.putExtra(Constants.KEY_IMG, Uri.parse(("content://media/external/images/media/337663")));


        activityTestRule.launchActivity(intent);

    }
    @Test
    public void testAgeMonth(){
        onView(withId(R.id.nameEdit))
                .perform(typeText("Clint Shaw"));

        onView(withId(R.id.emailEdit))
                .perform(typeText("test@test.com"));

        onView(withId(R.id.occEdit))
                .perform(typeText("occupation test"));

        onView(withId(R.id.descBox)).perform(typeText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Vestibulum eleifend odio volutpat nibh ultricies mattis. " +
                "Sed lobortis mauris ac turpis egestas, ut consequat ligula hendrerit. " +
                "Nullam tempus neque nec neque lacinia venenatis sit amet ornare dolor. " +
                "Praesent suscipit convallis orci sit amet fermentum. Mauris porta enim vitae congue ultricies. " +
                "Suspendisse elementum eleifend auctor. Sed commodo ante nec placerat aliquam."));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.birthdayEdit))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 9, 30));
        onView(withId(android.R.id.button1)).perform(click());

        Intent resultData = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        resultData.setData(Uri.parse(("content://media/external/images/media/337663")));
        Matcher<Intent> MediaPickIntent = allOf(hasAction(Intent.ACTION_PICK), hasData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
        Intents.init();
        intending(MediaPickIntent).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData));

        onView(withId(R.id.savedImage)).perform(click());
        intended(MediaPickIntent);
        Intents.release();

        onView(withId(R.id.submitBtn))
                .perform(scrollTo(), click());

        onView(withText(Constants.DOB_MSG))
                .check(matches(isDisplayed()))
                .inRoot(isDialog())
                .perform(click());

    }
}