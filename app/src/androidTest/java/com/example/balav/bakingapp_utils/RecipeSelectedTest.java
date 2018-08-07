package com.example.balav.bakingapp_utils;

import android.support.test.runner.AndroidJUnit4;

import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class RecipeSelectedTest {

    @Rule
    public ActivityTestRule<StepDetail> mActivityTestRule =
            new ActivityTestRule<>(StepDetail.class);

    @Test
    public void OnRecipesDisplayedTest(){
        //onView((withId(R.id.text_recipe))).check (matches(not(withText(""))));

        onView((withId(R.id.btn_next_step))).perform(click());

        onView((withId(R.id.tv_step_description))).check (matches(not(withText(""))));

    }

}
