package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.test.runner.AndroidJUnit4
import com.bayarsahintekin.domain.usecase.GamesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock


@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class GamesViewModelTest :BaseViewModelTest(){

    private lateinit var gamesViewModel: GamesViewModel

    @Mock
    lateinit var gamesUseCase: GamesUseCase



    @Before
    fun setup(){
        gamesViewModel = GamesViewModel(gamesUseCase, dispatchers = coroutineRule.testDispatcherProvider)
    }



}