package com.rbths.newstopheadlines.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rbths.newstopheadlines.network.FakeArticlesRepository
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.rbths.newstopheadlines.MainDispatcherRule
import com.rbths.newstopheadlines.model.ArticlesResponse
import com.rbths.newstopheadlines.model.Source
import org.junit.After
import org.junit.Rule
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(FakeArticlesRepository())
    }



    @Test
    fun `when getHeadlines called articlesLivedata is updated and it is not null`(){
        viewModel.getHeadlines()
        viewModel.articlesLiveData.observeForever {
            assertThat(it).isNotNull()
        }
    }

    @Test
    fun `when getHeadlines called articlesLivedata is updated and it returns the ArticlesResponse`(){
        viewModel.getHeadlines()
        viewModel.articlesLiveData.observeForever {
            assertThat(it).isInstanceOf(ArticlesResponse::class.java)
        }
    }
}