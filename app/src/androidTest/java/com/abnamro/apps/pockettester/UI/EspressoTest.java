package com.abnamro.apps.pockettester.UI;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.abnamro.apps.pockettester.PocketTesterActivity;
import com.abnamro.apps.pockettester.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityTestRule<PocketTesterActivity> mActivityRule =
            new ActivityTestRule<>(PocketTesterActivity.class);

    @Test
    public void findViewPerformActionAndCheckAssertion() {
        // Find Button and Click on it
//        onView(withId(R.id.btn_hello_android_testing)).perform(click());
//        onView(withId(R.id.btn_hello_android_testing)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
//
//        // Find TextView and verify the correct text that is displayed
//        onView(withId(R.id.text_view_rocks)).check(matches(withText(mActivityRule.getActivity().getString(R.string.android_testing_rocks))));
    }
}
