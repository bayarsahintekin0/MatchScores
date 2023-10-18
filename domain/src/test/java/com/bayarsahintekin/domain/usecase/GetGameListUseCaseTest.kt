package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.mock.GamesFactory
import com.bayarsahintekin.domain.repository.GameRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetGameListUseCaseTest {

    @Mock
    lateinit var gameRepository: GameRepository

    private lateinit var getGameListUseCase: GetGameListUseCase

    private val dummyGames = GamesFactory.generateDummyGameList(15)


    @Before
    fun setup(){
        getGameListUseCase = GetGameListUseCase(gameRepository)
    }

    @Test
    fun buildUseCaseObservable_callRepository() {
        getGameListUseCase.invoke()
        Mockito.verify(gameRepository).getGames()
    }

    @Test
    fun getGames() = runTest{
        Mockito.`when`(gameRepository.getGames()).thenReturn(
            dummyGames
        )
        val response = getGameListUseCase.invoke()
        Assert.assertEquals(dummyGames.first(),response.first())
    }
}