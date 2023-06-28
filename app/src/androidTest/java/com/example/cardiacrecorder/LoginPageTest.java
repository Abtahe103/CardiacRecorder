package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
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
    public void loginSuccessTest() throws InterruptedException {
        Intents.init();
        onView(withId(R.id.username_login)).perform(typeText("adnan"));
        onView(withId(R.id.email_login)).perform(typeText("adnan@gmail.com"));
        onView(withId(R.id.password_login)).perform(typeText("123456"));
        Espresso.pressBack();
        onView(withId(R.id.btn_login)).perform(click());

        Thread.sleep(2000);
        Intents.intended(IntentMatchers.hasComponent(HomePage.class.getName()));
        onView(withId(R.id.homepage_activity)).check(matches(isDisplayed()));
        Intents.release();

//        onView(withId(R.id.homepage_activiy)).check(matches(isDisplayed()));
    }
}