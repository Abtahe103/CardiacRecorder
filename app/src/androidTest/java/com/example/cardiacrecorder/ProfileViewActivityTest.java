package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class ProfileViewActivityTest {

    /**
     * checks whether all views are properly displayed
     */
    @Test
    public void displaytest() throws InterruptedException {


        ActivityScenarioRule<ProfileViewActivity> activityScenarioRule = new ActivityScenarioRule<ProfileViewActivity>(ProfileViewActivity.class);
        ActivityScenario.launch(ProfileViewActivity.class);

        Thread.sleep(3000);

        onView(withId(R.id.name_profile)).check(matches(isDisplayed()));
        onView(withId(R.id.mail_profile));

    }

}