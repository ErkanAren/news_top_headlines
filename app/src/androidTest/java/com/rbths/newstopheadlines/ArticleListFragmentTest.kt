package com.rbths.newstopheadlines

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.rbths.newstopheadlines.ui.ArticleListFragment
import com.rbths.newstopheadlines.ui.SplashFragmentDirections
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleListFragmentTest {


    @Test
    fun testIfRecyclerViewinArticleListFragment_isVisible() {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        // Create a graphical FragmentScenario for the TitleScreen
        val titleScenario = launchFragmentInContainer<ArticleListFragment>()

        titleScenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.navigation_graph)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
            val action = SplashFragmentDirections.actionSplashFragmentToArticleListFragment()
            navController.navigate(action)
        }


        assertThat(navController.currentDestination?.id).isEqualTo(R.id.articleListFragment)

        Espresso.onView(ViewMatchers.withId(R.id.articlesRV))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

}