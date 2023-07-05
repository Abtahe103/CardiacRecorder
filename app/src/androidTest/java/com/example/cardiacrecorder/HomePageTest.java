package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HomePageTest {

    /**
     * checks all cardviews are acting normal
     */
    @Test
    public void testHomePageView() throws InterruptedException {
        ActivityScenarioRule<HomePage> activityScenarioRule = new ActivityScenarioRule<HomePage>(HomePage.class);
        ActivityScenario.launch(HomePage.class);

        onView(withId(R.id.usernametext)).check(matches(isDisplayed()));
        onView(withId(R.id.add_cardview)).perform(click());
        onView(withId(R.id.add_record_view)).check(matches(isDisplayed()));
        Espresso.pressBack();


        onView(withId(R.id.records_cardview)).perform(click());
//        Thread.sleep(3000);
//        onView(withId(R.id.view_records)).check(matches(isDisplayed()));
        Espresso.pressBack();

        onView(withId(R.id.profile_cardview)).perform(click());
        onView(withId(R.id.profileview_activity)).check(matches(isDisplayed()));
        Espresso.pressBack();

        onView(withId(R.id.logout_cardview)).perform(click());
        onView(withId(R.id.loginview_activity)).check(matches(isDisplayed()));
    }
}