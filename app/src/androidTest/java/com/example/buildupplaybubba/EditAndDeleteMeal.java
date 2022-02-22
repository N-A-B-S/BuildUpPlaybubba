package com.example.buildupplaybubba;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditAndDeleteMeal {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACTIVITY_RECOGNITION");

    @Test
    public void editAndDeleteMeal() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.fab_meals),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.mealTitle),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Dinnelr"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.mealTitle), withText("Dinnelr"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(pressImeActionButton());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.mealCalories),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout3),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("250"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.mealCarbs),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout4),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("250"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.mealPro),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout5),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                4)),
                                1),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("60"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.mealPro), withText("60"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout5),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                4)),
                                1),
                        isDisplayed()));
        appCompatEditText6.perform(pressImeActionButton());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.mealFat),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout6),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                1),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("3"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.mealFat), withText("3"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout6),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                1),
                        isDisplayed()));
        appCompatEditText8.perform(pressImeActionButton());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.mealFat), withText("3"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout6),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                1),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("30"));

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.mealFat), withText("30"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout6),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                1),
                        isDisplayed()));
        appCompatEditText10.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.mealFibre),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout7),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                6)),
                                1),
                        isDisplayed()));
        appCompatEditText11.perform(replaceText("3"), closeSoftKeyboard());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.mealFibre), withText("3"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout7),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                6)),
                                1),
                        isDisplayed()));
        appCompatEditText12.perform(pressImeActionButton());

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.mealFibre), withText("3"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout7),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                6)),
                                1),
                        isDisplayed()));
        appCompatEditText13.perform(replaceText("30"));

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.mealFibre), withText("30"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout7),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                6)),
                                1),
                        isDisplayed()));
        appCompatEditText14.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.mealSugar),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout8),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                7)),
                                1),
                        isDisplayed()));
        appCompatEditText15.perform(replaceText("3"), closeSoftKeyboard());

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.mealSugar), withText("3"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout8),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                7)),
                                1),
                        isDisplayed()));
        appCompatEditText16.perform(pressImeActionButton());

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.mealSugar), withText("3"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout8),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                7)),
                                1),
                        isDisplayed()));
        appCompatEditText17.perform(replaceText("30"));

        ViewInteraction appCompatEditText18 = onView(
                allOf(withId(R.id.mealSugar), withText("30"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout8),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                7)),
                                1),
                        isDisplayed()));
        appCompatEditText18.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText19 = onView(
                allOf(withId(R.id.mealSodium),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout9),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                8)),
                                1),
                        isDisplayed()));
        appCompatEditText19.perform(replaceText("30"), closeSoftKeyboard());

        ViewInteraction appCompatEditText20 = onView(
                allOf(withId(R.id.mealSodium), withText("30"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout9),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                8)),
                                1),
                        isDisplayed()));
        appCompatEditText20.perform(pressImeActionButton());

        ViewInteraction appCompatEditText21 = onView(
                allOf(withId(R.id.mealPotassium),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout10),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                9)),
                                1),
                        isDisplayed()));
        appCompatEditText21.perform(replaceText("52"), closeSoftKeyboard());

        ViewInteraction appCompatEditText22 = onView(
                allOf(withId(R.id.mealPotassium), withText("52"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout10),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                9)),
                                1),
                        isDisplayed()));
        appCompatEditText22.perform(pressImeActionButton());

        ViewInteraction appCompatEditText23 = onView(
                allOf(withId(R.id.mealVitA),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout11),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                10)),
                                1),
                        isDisplayed()));
        appCompatEditText23.perform(replaceText("2"), closeSoftKeyboard());

        ViewInteraction appCompatEditText24 = onView(
                allOf(withId(R.id.mealVitA), withText("2"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout11),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                10)),
                                1),
                        isDisplayed()));
        appCompatEditText24.perform(pressImeActionButton());

        ViewInteraction appCompatEditText25 = onView(
                allOf(withId(R.id.mealVitA), withText("2"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout11),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                10)),
                                1),
                        isDisplayed()));
        appCompatEditText25.perform(replaceText("25"));

        ViewInteraction appCompatEditText26 = onView(
                allOf(withId(R.id.mealVitA), withText("25"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout11),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                10)),
                                1),
                        isDisplayed()));
        appCompatEditText26.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText27 = onView(
                allOf(withId(R.id.mealVitC),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout12),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                11)),
                                1),
                        isDisplayed()));
        appCompatEditText27.perform(replaceText("2"), closeSoftKeyboard());

        ViewInteraction appCompatEditText28 = onView(
                allOf(withId(R.id.mealVitC), withText("2"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout12),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                11)),
                                1),
                        isDisplayed()));
        appCompatEditText28.perform(pressImeActionButton());

        ViewInteraction appCompatEditText29 = onView(
                allOf(withId(R.id.mealVitC), withText("2"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout12),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                11)),
                                1),
                        isDisplayed()));
        appCompatEditText29.perform(replaceText("25"));

        ViewInteraction appCompatEditText30 = onView(
                allOf(withId(R.id.mealVitC), withText("25"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout12),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                11)),
                                1),
                        isDisplayed()));
        appCompatEditText30.perform(closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText31 = onView(
                allOf(withId(R.id.mealIron),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout14),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                13)),
                                1),
                        isDisplayed()));
        appCompatEditText31.perform(replaceText("25"), closeSoftKeyboard());

        ViewInteraction appCompatEditText32 = onView(
                allOf(withId(R.id.mealCalcium),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout13),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                12)),
                                1),
                        isDisplayed()));
        appCompatEditText32.perform(replaceText("8"), closeSoftKeyboard());

        ViewInteraction appCompatEditText33 = onView(
                allOf(withId(R.id.mealCalcium), withText("8"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout13),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                12)),
                                1),
                        isDisplayed()));
        appCompatEditText33.perform(pressImeActionButton());

        ViewInteraction appCompatEditText34 = onView(
                allOf(withId(R.id.mealIron), withText("25"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout14),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                13)),
                                1),
                        isDisplayed()));
        appCompatEditText34.perform(pressImeActionButton());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.saveMeal), withText("Save meal"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                14),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.browse), withContentDescription("Browse"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction cardView = onView(
                allOf(withId(R.id.Nutritioncv),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        cardView.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rv_mealRecycler),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.editButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatEditText35 = onView(
                allOf(withId(R.id.mealTitle), withText("Dinnelr"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText35.perform(replaceText("Dinner"));

        ViewInteraction appCompatEditText36 = onView(
                allOf(withId(R.id.mealTitle), withText("Dinner"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText36.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText37 = onView(
                allOf(withId(R.id.mealCarbs), withText("250"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout4),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText37.perform(replaceText("25"));

        ViewInteraction appCompatEditText38 = onView(
                allOf(withId(R.id.mealCarbs), withText("25"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout4),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText38.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText39 = onView(
                allOf(withId(R.id.mealCarbs), withText("25"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout4),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText39.perform(pressImeActionButton());

        pressBack();

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.editMeal), withText("Edit meal"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                14),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_mealTitle), withText("Dinner"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
                        isDisplayed()));
        textView.check(matches(withText("Dinner")));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.rv_mealRecycler),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.deleteButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.alertTitle), withText("Delete Meal?"),
                        withParent(allOf(withId(R.id.title_template),
                                withParent(withId(R.id.topPanel)))),
                        isDisplayed()));
        textView2.check(matches(withText("Delete Meal?")));

        ViewInteraction materialButton3 = onView(
                allOf(withId(android.R.id.button1), withText("Delete"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.rv_mealRecycler),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        recyclerView3.check(matches(isDisplayed()));
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
