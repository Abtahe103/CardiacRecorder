package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Test;

public class AddRecordActivityTest {

    @Test
    public void testAddRecordActivity() throws InterruptedException {

        HomePage.usrname="adnan";

        ActivityScenarioRule<AddRecordActivity> activityScenarioRule = new ActivityScenarioRule<AddRecordActivity>(AddRecordActivity.class);
        ActivityScenario.launch(AddRecordActivity.class);

        onView(withId(R.id.heart_rate_edit_text)).perform(typeText("96"));
        onView(withId(R.id.diastolic_pressure_edit_text)).perform(typeText("110"));
        onView(withId(R.id.systolic_pressure_edit_text)).perform(typeText("55"));
        onView(withId(R.id.date_edit_text)).perform(typeText("10-12-2021"));
        onView(withId(R.id.time_edit_text)).perform(typeText("10:12"));
        Espresso.pressBack();
        onView(withId(R.id.comment)).perform(typeText("abcd"));

        onView(withId(R.id.add_record_btn)).perform(click());
        Thread.sleep(2000);

//        onView(withText("Your message here")).inRoot(withDecorView(Matchers.not(activityScenarioRule.getActivity().getWindow().getDecorView())))
//                .check(matches(isDisplayed()));

        //onView(withText("R.id.ToastText")).inRoot(withDecorView(Matchers.not(AddRecordActivity.getWindow().getDecorView())))// Here you use decorView.check(matches(isDisplayed()));
//        onView(withText("Record Added")).check(matches(isDisplayed()));
    }
}