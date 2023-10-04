package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.test.runner.AndroidJUnit4
import com.bayarsahintekin.data.entity.games.toDomain
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.usecase.GamesFilterUseCase
import com.bayarsahintekin.domain.usecase.GamesUseCase
import com.bayarsahintekin.domain.usecase.TeamsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
    lateinit var gamesUseCase: GamesUseCase

    @Mock
    lateinit var gamesFilterUseCase: GamesFilterUseCase

    @Mock
    lateinit var teamsUseCase: TeamsUseCase



    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        gamesViewModel = GamesViewModel(gamesUseCase,gamesFilterUseCase,teamsUseCase, dispatchers = coroutineRule.testDispatcherProvider)
    }

    @Test
    fun getGamesSuccess_returnsTrue()= runTest {
        val testData: Flow<PagingData<GameEntity>>
        Mockito.`when`(gamesViewModel.games).thenReturn(testData)
    }


}