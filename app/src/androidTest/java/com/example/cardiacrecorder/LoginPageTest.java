package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LoginPageTest {
    @Rule
    public ActivityScenarioRule<LoginPage> activityScenarioRule = new ActivityScenarioRule<>(LoginPage.class);
    @Test
    public void testLoginPageView(){
        onView(withId(R.id.email_login)).perform(ViewActions.typeText("adnan@gmail.com"));
        onView(withId(R.id.password_login)).perform(ViewActions.typeText(String.valueOf(123456)));
        onView(withId(R.id.btn_login)).perform(click());

        onView(withId(R.id.homepage)).check(matches(isDisplayed()));
    }
}