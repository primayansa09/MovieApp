package com.example.moviecatalogue.ui.home;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.ui.data.MovieEntity;
import com.example.moviecatalogue.ui.until.DataDummy;

import org.junit.Rule;
import org.junit.Test;
import java.util.ArrayList;

public class HomeActivityTest {
    private ArrayList<MovieEntity> dummyMovie = DataDummy.generateDummyMovie();
    private ArrayList<MovieEntity> dummyTv = DataDummy.generateDummyTv();

    /**
        Setup Preview
     */
    @Rule
    public ActivityScenarioRule activityRule = new ActivityScenarioRule<>(HomeActivity.class);

    /**
     Action on icon change language
     */
    @Test
    public void clickCahngeLanguage(){
        onView(withId(R.id.img_setting)).perform(click());
        onView(withId(R.id.lyt_bahasa)).perform(click());
    }

    /**
     Action on icon darkmode
     */
    @Test
    public void clickDarkmode(){
        onView(withId(R.id.img_setting)).perform(click());
        onView(withId(R.id.switchThme)).perform(click());
    }

    /**
      Testing load data movie
     */
    @Test
    public void loadMovie(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition(dummyMovie.size()));
    }

    /**
      Testing move fragment to TV Show
     */
    @Test
    public void loadTvShow(){
        onView(withText("TV SHOW")).perform(click());
        onView(withId(R.id.rv_tvShow)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tvShow)).perform(RecyclerViewActions.scrollToPosition(dummyTv.size()));
    }

    /**
     Testing load detail data movie
     */
    @Test
    public void loadDetailMovie(){
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tvTitleDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvTitleDetail)).check(matches(withText(dummyMovie.get(0).getTitle())));
        onView(withId(R.id.tvReleaseDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvReleaseDetail)).check(matches(withText(dummyMovie.get(0).getReleaseDate())));
        onView(withId(R.id.tvCategoryDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvCategoryDetail)).check(matches(withText(dummyMovie.get(0).getCategory())));
        onView(withId(R.id.tvDurationDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvDurationDetail)).check(matches(withText(dummyMovie.get(0).getDuration())));
        onView(withId(R.id.tvDescDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvDescDetail)).check(matches(withText(dummyMovie.get(0).getOverview())));
        onView(withId(R.id.img_share)).perform(click());
    }

    /**
     Testing load detail data tv show
     */
    @Test
    public void loadDetailTvShow(){
        onView(withText("TV SHOW")).perform(click());
        onView(withId(R.id.rv_tvShow)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tvTitleDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvTitleDetail)).check(matches(withText(dummyTv.get(0).getTitle())));
        onView(withId(R.id.tvReleaseDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvReleaseDetail)).check(matches(withText(dummyTv.get(0).getReleaseDate())));
        onView(withId(R.id.tvCategoryDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvCategoryDetail)).check(matches(withText(dummyTv.get(0).getCategory())));
        onView(withId(R.id.tvDurationDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvDurationDetail)).check(matches(withText(dummyTv.get(0).getDuration())));
        onView(withId(R.id.tvDescDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvDescDetail)).check(matches(withText(dummyTv.get(0).getOverview())));
        onView(withId(R.id.img_share)).perform(click());
    }

    /**
     Actiion on icon backDetail
     */
    @Test
    public void clickBackDetail(){
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.img_backDetail)).perform(click());

    }

    /**
     Action on icon backSetting
     */
    @Test
    public void clickBackSetting(){
        onView(withId(R.id.img_setting)).perform(click());
        onView(withId(R.id.img_backSetting)).perform(click());
    }
}