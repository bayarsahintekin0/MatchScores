package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.mock.PlayerFactory
import com.bayarsahintekin.domain.repository.PlayersRepository
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
class GetPlayerListUseCaseTest {

    @Mock
    lateinit var playerRepository: PlayersRepository

    private lateinit var getPlayerListUseCase: GetPlayerListUseCase

    private val dummyPlayers = PlayerFactory.generateDummyPlayerList(12)


    @Before
    fun setup() {
        getPlayerListUseCase = GetPlayerListUseCase(playerRepository)
    }

    @Test
    fun buildUseCaseObservable_callRepository() {
        getPlayerListUseCase.invoke()
        Mockito.verify(playerRepository).players()
    }

    @Test
    fun getGames() = runTest {
        Mockito.`when`(playerRepository.players()).thenReturn(
            dummyPlayers
        )
        val response = getPlayerListUseCase.invoke()
        Assert.assertEquals(dummyPlayers.first(), response.first())
    }
}
