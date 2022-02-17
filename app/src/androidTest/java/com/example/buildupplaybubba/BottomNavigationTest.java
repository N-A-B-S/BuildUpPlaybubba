package com.example.buildupplaybubba;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BottomNavigationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACTIVITY_RECOGNITION");

    @Test
    public void bottomNavigationTest() {
        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.home), withContentDescription("Home"),
                        withParent(withParent(withId(R.id.bottom_navigation))),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));

        ViewInteraction frameLayout2 = onView(
                allOf(withId(R.id.journal), withContentDescription("Journal"),
                        withParent(withParent(withId(R.id.bottom_navigation))),
                        isDisplayed()));
        frameLayout2.check(matches(isDisplayed()));

        ViewInteraction frameLayout3 = onView(
                allOf(withId(R.id.browse), withContentDescription("Browse"),
                        withParent(withParent(withId(R.id.bottom_navigation))),
                        isDisplayed()));
        frameLayout3.check(matches(isDisplayed()));

        ViewInteraction frameLayout4 = onView(
                allOf(withId(R.id.browse), withContentDescription("Browse"),
                        withParent(withParent(withId(R.id.bottom_navigation))),
                        isDisplayed()));
        frameLayout4.check(matches(isDisplayed()));

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.journal), withContentDescription("Journal"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.browse), withContentDescription("Browse"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.Steps), withText("Steps"),
                        withParent(withParent(withId(R.id.Stepscv))),
                        isDisplayed()));
        textView.check(matches(withText("Steps")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.Nutrition), withText("Nutrition"),
                        withParent(withParent(withId(R.id.Nutritioncv))),
                        isDisplayed()));
        textView2.check(matches(withText("Nutrition")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.Sleep), withText("Sleep"),
                        withParent(withParent(withId(R.id.Sleepcv))),
                        isDisplayed()));
        textView3.check(matches(withText("Sleep")));

        ViewInteraction bottomNavigationItemView3 = onView(
                allOf(withId(R.id.journal), withContentDescription("Journal"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView3.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rv_activityRecycler),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
