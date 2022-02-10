package com.example.moviecatalogue.ui.home;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.until.DataDummy;
import com.example.moviecatalogue.ui.until.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import java.util.ArrayList;

public class HomeActivityTest {
    private ArrayList<MovieResultsItem> dummyMovie = DataDummy.generateDummyMovie();
    private ArrayList<TvResultsItem> dummyTv = DataDummy.generateDummyTv();

    @Rule
    public ActivityScenarioRule activityRule = new ActivityScenarioRule<>(HomeActivity.class);

    /**
        Setup Preview
     */

    @Before
    public void setup(){
        ActivityScenario.launch(HomeActivity.class);
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdResource());
    }

    /**
     Action on icon change language
     */
    @Test
    public void clickChangeLanguage(){
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
        onView(withId(R.id.imgDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvOriginalTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.tvOriginalTitle)).check(matches(withText(dummyMovie.get(0).getOriginalTitle())));
        onView(withId(R.id.tvTitleDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvTitleDetail)).check(matches(withText(dummyMovie.get(0).getTitle())));
        onView(withId(R.id.tvoriginal_lenguage)).check(matches(isDisplayed()));
        onView(withId(R.id.tvoriginal_lenguage)).check(matches(withText(dummyMovie.get(0).getOriginalLanguage())));
        onView(withId(R.id.tvReleaseDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvReleaseDetail)).check(matches(withText(dummyMovie.get(0).getReleaseDate())));
        onView(withId(R.id.tvDescDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvDescDetail)).check(matches(withText(dummyMovie.get(0).getOverview())));
        onView(withId(R.id.rantingBar)).check(matches(isDisplayed()));
        onView(withId(R.id.img_favDetail)).perform(click());
        onView(withId(R.id.img_share)).perform(click());
    }

    /**
     Testing load detail data tv show
     */
    @Test
    public void loadDetailTvShow(){
        onView(withText("TV SHOW")).perform(click());
        onView(withId(R.id.rv_tvShow)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.imgDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvOriginalTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.tvOriginalTitle)).check(matches(withText(dummyTv.get(0).getOriginalName())));
        onView(withId(R.id.tvTitleDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvTitleDetail)).check(matches(withText(dummyTv.get(0).getName())));
        onView(withId(R.id.tvReleaseDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvReleaseDetail)).check(matches(withText(dummyTv.get(0).getFirstAirDate())));
        onView(withId(R.id.tvoriginal_lenguage)).check(matches(isDisplayed()));
        onView(withId(R.id.tvoriginal_lenguage)).check(matches(withText(dummyTv.get(0).getOriginalLanguage())));
        onView(withId(R.id.tvDescDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvDescDetail)).check(matches(withText(dummyTv.get(0).getOverview())));
        onView(withId(R.id.rantingBar)).check(matches(isDisplayed()));
        onView(withId(R.id.img_favDetail)).perform(click());
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

    /**
     Testing load detail favorite movie
     */
    @Test
    public void loadFavMoviesDetail(){
        onView(withId(R.id.img_favorite)).perform(click());
        onView(withId(R.id.rv_movieFav)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.imgDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvOriginalTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.tvOriginalTitle)).check(matches(withText(dummyMovie.get(0).getOriginalTitle())));
        onView(withId(R.id.tvTitleDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvTitleDetail)).check(matches(withText(dummyMovie.get(0).getTitle())));
        onView(withId(R.id.tvoriginal_lenguage)).check(matches(isDisplayed()));
        onView(withId(R.id.tvoriginal_lenguage)).check(matches(withText(dummyMovie.get(0).getOriginalLanguage())));
        onView(withId(R.id.tvReleaseDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvReleaseDetail)).check(matches(withText(dummyMovie.get(0).getReleaseDate())));
        onView(withId(R.id.tvDescDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvDescDetail)).check(matches(withText(dummyMovie.get(0).getOverview())));
        onView(withId(R.id.rantingBar)).check(matches(isDisplayed()));
        onView(withId(R.id.img_favDetail)).perform(click());

    }


    /**
     Testing load detail favorite tv show
     */
    @Test
    public void loadFavTvShowDetail(){
        onView(withId(R.id.img_favorite)).perform(click());
        onView(withText("FAVORITE TV")).perform(click());
        onView(withId(R.id.rv_tvFav)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.imgDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvOriginalTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.tvOriginalTitle)).check(matches(withText(dummyTv.get(0).getOriginalName())));
        onView(withId(R.id.tvTitleDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvTitleDetail)).check(matches(withText(dummyTv.get(0).getName())));
        onView(withId(R.id.tvReleaseDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvReleaseDetail)).check(matches(withText(dummyTv.get(0).getFirstAirDate())));
        onView(withId(R.id.tvoriginal_lenguage)).check(matches(isDisplayed()));
        onView(withId(R.id.tvoriginal_lenguage)).check(matches(withText(dummyTv.get(0).getOriginalLanguage())));
        onView(withId(R.id.tvDescDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvDescDetail)).check(matches(withText(dummyTv.get(0).getOverview())));
        onView(withId(R.id.rantingBar)).check(matches(isDisplayed()));
        onView(withId(R.id.img_favDetail)).perform(click());
    }

    /**
     Action on icon favorite
     */
    @Test
    public void clickIconFavorite(){
        onView(withId(R.id.img_favorite)).perform(click());
        onView(withId(R.id.img_backFavorite)).perform(click());
    }

    @After
    public void tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdResource());
    }

}