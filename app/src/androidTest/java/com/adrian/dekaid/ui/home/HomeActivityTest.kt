package com.adrian.dekaid.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.adrian.dekaid.R
import com.adrian.dekaid.utils.DataDummy
import com.adrian.dekaid.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    private val dummyMovie = DataDummy.getMovieData()
    private val dummyShow = DataDummy.getShowData()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadMovieDetail() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                MyViewAction.clickChildViewWithId(R.id.button_detail)
            )
        )
        onView(withId(R.id.favorite_button)).perform(click())
        onView(withId(R.id.image_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.image_detail)).check(matches(withTagValue(equalTo(dummyMovie[0].movieImage))))
        onView(withId(R.id.collap_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.app_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_title_detail)).check(matches(withText(dummyMovie[0].movieTitle)))
        onView(withId(R.id.duration_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.duration_detail)).check(matches(withText("${dummyMovie[0].movieDuration} min")))
        onView(withId(R.id.vote_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_detail)).check(matches(withText("${dummyMovie[0].movieVote}")))
        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
        onView(withId(R.id.release_year)).check(matches(withText(dummyMovie[0].movieReleaseYear)))
    }

    @Test
    fun loadShow() {
        onView(withId(R.id.tvshow)).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyShow.size
            )
        )
    }

    @Test
    fun loadShowDetail() {
        onView(withId(R.id.tvshow)).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                MyViewAction.clickChildViewWithId(R.id.button_detail)
            )
        )
        onView(withId(R.id.favorite_button)).perform(click())
        onView(withId(R.id.image_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.image_detail)).check(matches(withTagValue(equalTo(dummyShow[0].showImage))))
        onView(withId(R.id.collap_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.app_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_title_detail)).check(matches(withText(dummyShow[0].showName)))
        onView(withId(R.id.duration_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.duration_detail)).check(matches(withText("${dummyShow[0].showSeason} seasons")))
        onView(withId(R.id.vote_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_detail)).check(matches(withText(dummyShow[0].showVote.toString())))
        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
        onView(withId(R.id.release_year)).check(matches(withText(dummyShow[0].showFirstAir)))
    }

    @Test
    fun loadFavoriteMovie() {
        onView(withId(R.id.favorite)).perform(click())
        onView(withId(R.id.rv_movie_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_favorite)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyShow.size
            )
        )
    }

    @Test
    fun loadFavoriteShow() {
        onView(withId(R.id.favorite)).perform(click())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_show_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_show_favorite)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyShow.size
            )
        )
    }

    @Test
    fun loadFavoriteMovieDetail() {
        onView(withId(R.id.favorite)).perform(click())
        onView(withId(R.id.rv_movie_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.rv_movie_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                MyViewAction.clickChildViewWithId(R.id.button_detail)
            )
        )
        onView(withId(R.id.favorite_button)).perform(click())
        onView(withId(R.id.image_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.collap_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.app_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.duration_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavoriteShowDetail() {
        onView(withId(R.id.favorite)).perform(click())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_show_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.rv_show_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                MyViewAction.clickChildViewWithId(R.id.button_detail)
            )
        )
        onView(withId(R.id.favorite_button)).perform(click())
        onView(withId(R.id.image_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.collap_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.app_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.duration_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
    }

    object MyViewAction {
        fun clickChildViewWithId(id: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? {
                    return null
                }

                override fun getDescription(): String {
                    return "Click on a child view with specified id."
                }

                override fun perform(uiController: UiController?, view: View) {
                    val view: View = view.findViewById(id)
                    view.performClick()
                }
            }
        }
    }
}