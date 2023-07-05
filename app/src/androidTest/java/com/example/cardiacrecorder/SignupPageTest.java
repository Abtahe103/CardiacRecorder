package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class SignupPageTest {
    @Rule
    public ActivityScenarioRule<SignupPage> activityScenarioRule = new ActivityScenarioRule<>(SignupPage.class);

    /**
     * checks whether all views are properly displayed
     */
    @Test
    public void displayTest() {
        onView(withId(R.id.Name)).check(matches(isDisplayed()));
        onView(withId(R.id.user_signup)).check(matches(isDisplayed()));
        onView(withId(R.id.email_signup)).check(matches(isDisplayed()));
        onView(withId(R.id.password_signup)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_signup)).check(matches(isDisplayed()));
    }

    /**
     * checks if it can successfully signup
     */
    @Test
    public void signupSuccessTest() throws InterruptedException {

        onView(withId(R.id.Name)).perform(typeText("abcd123"));
        onView(withId(R.id.user_signup)).perform(typeText("abcd123"));
        onView(withId(R.id.email_signup)).perform(typeText("hp@gmail.com"));
        Espresso.pressBack();
        onView(withId(R.id.password_signup)).perform(typeText("123456"));
        Espresso.pressBack();
        onView(withId(R.id.btn_signup)).perform(click());

        Intents.init();
        Thread.sleep(3000);
        Intents.intended(IntentMatchers.hasComponent(HomePage.class.getName()));
        onView(withId(R.id.homepage_activity)).check(matches(isDisplayed()));
        Intents.release();
    }
}