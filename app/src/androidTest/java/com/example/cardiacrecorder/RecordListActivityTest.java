package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.*;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class RecordListActivityTest {
    /**
     * checks if all the views are properly displayed
     * @throws InterruptedException
     */
    @Test
    public void displaytest() throws InterruptedException {
        String usrname = "3tlXhQvx8ud4tzMX8XifBCHmgpJ3";

        ActivityScenarioRule<RecordListActivity> activityScenarioRule = new ActivityScenarioRule<RecordListActivity>(RecordListActivity.class);
        ActivityScenario.launch(RecordListActivity.class);

        Thread.sleep(3000);

        onView(withId(R.id.recyclerViewId)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.delete_btn)).check(matches(isDisplayed()));

    }
}