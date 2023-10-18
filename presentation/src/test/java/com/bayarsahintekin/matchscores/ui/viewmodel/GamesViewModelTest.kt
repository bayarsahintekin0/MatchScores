package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.paging.PagingData
import androidx.paging.map
import androidx.test.runner.AndroidJUnit4
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.usecase.FilterGamesUseCase
import com.bayarsahintekin.domain.usecase.GetGameListUseCase
import com.bayarsahintekin.domain.usecase.GetTeamByIdUseCase
import com.bayarsahintekin.domain.usecase.GetTeamListUseCase
import com.bayarsahintekin.matchscores.base.BaseViewModelTest
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class GamesViewModelTest : BaseViewModelTest(){

    private lateinit var gamesViewModel: GamesViewModel

    @Mock
    lateinit var getGameListUseCase: GetGameListUseCase

    @Mock
    lateinit var filterGamesUseCase: FilterGamesUseCase

    @Mock
    lateinit var getTeamListUseCase: GetTeamListUseCase

    @Mock
    lateinit var getTeamByIdUseCase: GetTeamByIdUseCase



    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        gamesViewModel = GamesViewModel(
            getGameListUseCase = getGameListUseCase,
            filterGamesUseCase = filterGamesUseCase,
            getTeamByIdUseCase = getTeamByIdUseCase,
            getTeamListUseCase = getTeamListUseCase,
            dispatchers = coroutineRule.testDispatcherProvider
        )
    }




}