package com.rbths.newstopheadlines


import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth

import com.rbths.newstopheadlines.ui.*

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SplashToArticleNavigation {

    @get:Rule
    val disableAnimationsRule = DisableAnimationsRule()

    @Test
    fun splashFragment_isVisible() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        val titleScenario = launchFragmentInContainer<SplashFragment>()

        titleScenario.onFragment { fragment ->
            //Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.navigation_graph)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)

        }
        Truth.assertThat(navController.currentDestination?.id).isEqualTo(R.id.splashFragment)
    }



}