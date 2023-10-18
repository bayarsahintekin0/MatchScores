package com.bayarsahintekin.domain.usecase

import androidx.paging.map
import com.bayarsahintekin.domain.mock.GamesFactory
import com.bayarsahintekin.domain.repository.GameRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class FilterGamesUseCaseTest {

    @Mock
    lateinit var gameRepository: GameRepository

    private lateinit var filterGamesUseCase: FilterGamesUseCase

    private val dummyGames = GamesFactory.generateDummyGameList(12)

    @Before
    fun setup(){
        filterGamesUseCase = FilterGamesUseCase(gameRepository)
    }

    @Test
    fun buildUseCaseObservable_callRepository() {
        filterGamesUseCase.invoke(1,2019)

        verify(gameRepository).getFilterGames(1,2019)
    }

    @Test
    fun filterGamesWithFullParameter() {
        `when`(gameRepository.getFilterGames(1,2019)).thenReturn(
            dummyGames
        )
        val response = filterGamesUseCase.invoke(1,2019)
        Assert.assertEquals(dummyGames,response)
    }

    @Test
    fun filterGamesWithOnlyTeamId() {
        `when`(gameRepository.getFilterGames(1,null)).thenReturn(
            dummyGames
        )
        val  response = filterGamesUseCase.invoke(1,null)
        Assert.assertEquals(dummyGames,response)
    }

    @Test
    fun filterGamesWithOnlySeason() {
        `when`(gameRepository.getFilterGames(null,2020)).thenReturn(
            dummyGames
        )
        val  response = filterGamesUseCase.invoke(null,2020)
        Assert.assertEquals(dummyGames,response)
    }

    @Test
    fun filterGamesWithNoParameter() {
        `when`(gameRepository.getFilterGames(null,null)).thenReturn(
            dummyGames
        )
        val  response = filterGamesUseCase.invoke(null,null)
        Assert.assertEquals(dummyGames,response)
    }
}