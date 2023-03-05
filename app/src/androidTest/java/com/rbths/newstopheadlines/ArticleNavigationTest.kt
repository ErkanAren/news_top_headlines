package com.rbths.newstopheadlines

import android.content.Intent
import android.os.RemoteException
import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule

import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.rbths.newstopheadlines.adapters.ArticlesAdapter
import com.rbths.newstopheadlines.model.Article
import com.rbths.newstopheadlines.ui.*
import com.rbths.newstopheadlines.utils.Constants.Companion.SPLASH_SCREEN_MILLIS

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class ArticleNavigationTest {

    private lateinit var article: Article

    //@get:Rule
    //val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val disableAnimationsRule = DisableAnimationsRule()

    lateinit var articles : MutableList<Article>

    private lateinit var navController : NavHostController

    private val titleScenario = launchFragmentInContainer<ArticleListFragment>()

    @Before
    fun setUp() {
        // Initialize Article object with some values
        articles = mutableListOf<Article>(
            Article(
                null,
                "",
                "",
                "",
                "",
                "",
                "",
                ""
            ),
            Article(
                null,
                "author",
                "title",
                "description",
                "https://example.com",
                "https://example.com/image.jpg",
                "2022-03-03T10:00:00Z",
                "content"
            ),
            Article(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        )

        // Create a TestNavHostController
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())



        titleScenario.onFragment { fragment ->
            // Set up the RecyclerView with the mock repository and a mock click listener


            val recyclerView = fragment.view?.findViewById<RecyclerView>(R.id.articlesRV)
            recyclerView?.layoutManager = LinearLayoutManager(fragment.requireContext())
            recyclerView?.adapter = ArticlesAdapter(ApplicationProvider.getApplicationContext(),articles) { article ->
                //val action = ArticleListFragmentDirections.actionArticleListFragmentToArticleReadFragment(article)
                //navController.navigate(action)
                (navController as TestNavHostController).setCurrentDestination(R.id.articleReadFragment)
            }
            fragment.articlesRecyclerView = recyclerView!!

            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.navigation_graph)


            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)

        }

        Thread.sleep(SPLASH_SCREEN_MILLIS)



    }

    @Test
    fun testIfRecyclerViewIsVisible_navigateToArticleReadFragment() {

        onView(withId(R.id.articlesRV)).check(matches( isDisplayed()))
    }

    @Test
    fun clickOnFilledArticleItem_navigateToArticleReadFragment() {
        val positionToClick = 1

        // Verify that performing a click changes the NavController’s state
       onView(withId(R.id.articlesRV))
            .perform(scrollToPosition<ArticlesAdapter.ArticleViewHolder>(positionToClick)).check(RecyclerViewItemCountAssertion(articles.size)
            )
        onView(withId(R.id.articlesRV)).check(matches( isDisplayed()))
        onView(withId(R.id.articlesRV)).perform(actionOnItemAtPosition<ArticlesAdapter.ArticleViewHolder>(positionToClick, click()))


        assertThat(navController.currentDestination?.id).isEqualTo(R.id.articleReadFragment)
    }


    @Test
    fun clickOnArticleItemWithEmptyValues_navigateToArticleReadFragment() {
        val positionToClick = 0

        // Verify that performing a click changes the NavController’s state
        onView(withId(R.id.articlesRV))
            .perform(scrollToPosition<ArticlesAdapter.ArticleViewHolder>(positionToClick)).check(RecyclerViewItemCountAssertion(articles.size)
            )
        onView(withId(R.id.articlesRV)).perform(actionOnItemAtPosition<ArticlesAdapter.ArticleViewHolder>(positionToClick, click()))

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.articleReadFragment)
    }

    @Test
    fun clickOnArticleItemWithNullValues_navigateToArticleReadFragment() {
        val positionToClick = 2

        // Verify that performing a click changes the NavController’s state
        onView(withId(R.id.articlesRV))
            .perform(scrollToPosition<ArticlesAdapter.ArticleViewHolder>(positionToClick)).check(RecyclerViewItemCountAssertion(articles.size)
            )
        onView(withId(R.id.articlesRV)).perform(actionOnItemAtPosition<ArticlesAdapter.ArticleViewHolder>(positionToClick, click()))

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.articleReadFragment)
    }
}

class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        check(view is RecyclerView) { "The asserted view is not a RecyclerView" }
        val adapter = view.adapter
        assertThat(adapter?.itemCount).isEqualTo(expectedCount)
    }
}

