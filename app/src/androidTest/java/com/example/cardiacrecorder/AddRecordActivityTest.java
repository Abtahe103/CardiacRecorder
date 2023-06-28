package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.annotation.NonNull;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class AddRecordActivityTest {

    @Rule
    public ActivityScenarioRule<AddRecordActivity> activityScenarioRule = new ActivityScenarioRule<AddRecordActivity>(AddRecordActivity.class);
    @Rule
//    public FirebaseDatabaseTestRule firebaseRule = new FirebaseDatabaseTestRule();
    @Before
//    public void setup(){
//        AddRecordActivity.usrname="adnan";
//        HomePage.usrname="adnan";
//
//        FirebaseApp.initializeApp(getApplicationContext());
//        // Use the FirebaseDatabase instance provided by the test rule
//        FirebaseDatabase firebaseDatabase = firebaseRule.getFirebaseDatabase();
//        // Set the Firebase database instance in your AddRecordActivity
//        AddRecordActivity.databaseReference = firebaseDatabase.getReference().child("Records").child("adnan");
//    }
    @Test
    public void testAddRecordActivity(){
//        ActivityScenario.launch(AddRecordActivity.class);
        AddRecordActivity.usrname="adnan";
        ActivityScenario<AddRecordActivity> scenario = activityScenarioRule.getScenario();
        scenario.onActivity(activity -> {
            // Manually initialize databaseReference and wait for it to be ready
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Records").child(AddRecordActivity.usrname);
            CountDownLatch latch = new CountDownLatch(1);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    activity.databaseReference = databaseReference;
                    activity.child_count = snapshot.getChildrenCount();
                    latch.countDown(); // Notify that the initialization is complete
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    latch.countDown(); // Notify that the initialization is complete even in case of error
                }
            });

            try {
                latch.await(); // Wait until the initialization is complete
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        onView(withId(R.id.heart_rate_edit_text)).perform(typeText("96"));
        onView(withId(R.id.diastolic_pressure_edit_text)).perform(typeText("110"));
        onView(withId(R.id.systolic_pressure_edit_text)).perform(typeText("55"));
        onView(withId(R.id.date_edit_text)).perform(typeText("10-12-2021"));
        onView(withId(R.id.time_edit_text)).perform(typeText("10:12"));
        onView(withId(R.id.comment)).perform(typeText("abcd"));

        onView(withId(R.id.add_record_btn)).perform(click());

        onView(withText("Record Added")).check(matches(isDisplayed()));
    }
}