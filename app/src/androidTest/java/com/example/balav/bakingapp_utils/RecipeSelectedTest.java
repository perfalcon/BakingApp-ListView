package com.example.balav.bakingapp_utils;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.runner.AndroidJUnit4;

import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.balav.bakingapp_utils.model.Baking;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class RecipeSelectedTest {


    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    Baking baking;

     public void prepBakingRecipe(){
        baking =activityRule.getActivity ().mBaking.get (1);
    }


    @Test
    public void OnRecipesDisplayedTest(){

       // onView(withId(R.id.rv_recipe)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
      /*  onView(withId(R.id.rv_recipe)).perform(RecyclerViewActions.scrollToPosition(2));
        onView(withId(R.id.text_recipe)).check(matches(not(withText(""))));
*/
        onView(withId(R.id.rv_recipe)).perform(
                RecyclerViewActions.scrollToHolder(
                        withHolderRecipe ("Cheesecake")
                )
        ).perform (click ());

    }


    @Test
    public void OnRecipeStepsDisplayedTest(){
        onView(withId(R.id.rv_recipe)).perform(
                RecyclerViewActions.scrollToHolder(
                        withHolderRecipe ("Cheesecake")
                )
        ).perform (click ());

        prepBakingRecipe ();


        ActivityTestRule<DetailActivity> activityDetailRule = new ActivityTestRule<DetailActivity>(DetailActivity.class){
            @Override
            protected Intent getActivityIntent() {
                Intent intent = new Intent (InstrumentationRegistry.getContext(),MainActivity.class);
                intent.putExtra(DetailActivity.BAKING_KEY,baking);
                return intent;
            }
        };

     //   activityDetailRule.getActivity ().getSupportFragmentManager ().beginTransaction ();

        onData(anything()).inAdapterView(withId(R.id.lv_steps)).atPosition(0).perform(click());
        //onView(withId(R.id.tea_name_text_view)).check(matches(withText(TEA_NAME)));
    }

    public static Matcher<RecyclerView.ViewHolder> withHolderRecipe(final String text) {
        return new BoundedMatcher<RecyclerView.ViewHolder, RecipeAdapter.RecipeViewHolder>(RecipeAdapter.RecipeViewHolder.class) {

            @Override
            protected boolean matchesSafely(RecipeAdapter.RecipeViewHolder item) {
                Log.v("In Matcher","matchSafely");
                TextView recipeText = (TextView) item.itemView.findViewById(R.id.text_recipe);
                if (recipeText == null) {
                    return false;
                }
                return recipeText.getText().toString().contains(text);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("No ViewHolder found with text: " + text);
            }
        };
    }
}
