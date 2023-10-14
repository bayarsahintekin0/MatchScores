package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.paging.PagingData
import androidx.test.runner.AndroidJUnit4
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.usecase.FilterGamesUseCase
import com.bayarsahintekin.domain.usecase.GetGameListUseCase
import com.bayarsahintekin.domain.usecase.GetTeamListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class GamesViewModelTest :BaseViewModelTest(){

    private lateinit var gamesViewModel: GamesViewModel

    @Mock
    lateinit var getGameListUseCase: GetGameListUseCase

    @Mock
    lateinit var filterGamesUseCase: FilterGamesUseCase

    @Mock
    lateinit var getTeamListUseCase: GetTeamListUseCase



    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        gamesViewModel = GamesViewModel(getGameListUseCase,filterGamesUseCase,getTeamListUseCase, dispatchers = coroutineRule.testDispatcherProvider)
    }

    @Test
    fun getGamesSuccess_returnsTrue()= runTest {
        val testData: Flow<PagingData<GameEntity>>

    }


}