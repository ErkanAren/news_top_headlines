package com.rbths.newstopheadlines

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rbths.newstopheadlines.adapters.ArticlesAdapter
import com.rbths.newstopheadlines.ui.*
import com.rbths.newstopheadlines.utils.Constants.Companion.SPLASH_SCREEN_MILLIS
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashToArticleNavigation {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val disableAnimationsRule = DisableAnimationsRule()

    private lateinit var navController : NavHostController

    val titleScenario = launchFragmentInContainer<SplashFragment>()

    @Before
    fun setUp(){
        // Create a TestNavHostController
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())



        titleScenario.onFragment { fragment ->

            // Do nothing for now
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.navigation_graph)


            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
            
        }
    }


    @Test
    fun testIfRecyclerViewIsVisible_afterSplasScreen() {
        Thread.sleep(SPLASH_SCREEN_MILLIS)
        Espresso.onView(ViewMatchers.withId(R.id.articlesRV))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}