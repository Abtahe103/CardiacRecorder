package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LoginPageTest {
    @Rule
    public ActivityScenarioRule<LoginPage> activityScenarioRule = new ActivityScenarioRule<>(LoginPage.class);

    /**
     * this test function checks if everything in login page is displayed properly
     */
    @Test
    public void displaytest(){
        onView(withId(R.id.email_login)).check(matches(isDisplayed()));
        onView(withId(R.id.password_login)).check(matches(isDisplayed()));
    }

    /**
     * this test function types in all the editext and perform login
     * @throws InterruptedException
     */
    @Test
    public void loginSuccessTest() throws InterruptedException {
       onView(withId(R.id.email_login)).perform(typeText("adnan@gmail.com"));
        onView(withId(R.id.password_login)).perform(typeText("123456"));
        Espresso.pressBack();
        onView(withId(R.id.btn_login)).perform(click());

        Thread.sleep(3000);

        onView(withId(R.id.homepage)).check(matches(isDisplayed()));
    }
}