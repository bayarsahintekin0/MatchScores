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
class SearchPlayerUseCaseTest{

    @Mock
    lateinit var playerRepository: PlayersRepository

    private lateinit var searchPlayerUseCase: SearchPlayerUseCase

    private val dummyPlayers = PlayerFactory.generateDummyPlayerList(12)


    @Before
    fun setup() {
        searchPlayerUseCase = SearchPlayerUseCase(playerRepository)
    }

    @Test
    fun buildUseCaseObservable_callRepository() {
        searchPlayerUseCase.invoke("leb")
        Mockito.verify(playerRepository).searchPlayer("leb")
    }

    @Test
    fun getGames() = runTest {
        Mockito.`when`(playerRepository.searchPlayer("leb")).thenReturn(
            dummyPlayers
        )
        val response = searchPlayerUseCase.invoke("leb")
        Assert.assertEquals(dummyPlayers.first(), response.first())
    }
}