package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.mock.PlayerFactory
import com.bayarsahintekin.domain.mock.TeamFactory
import com.bayarsahintekin.domain.repository.PlayersRepository
import com.bayarsahintekin.domain.repository.TeamRepository
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
class GetTeamListUseCaseTest {

    @Mock
    lateinit var teamRepository: TeamRepository

    private lateinit var getTeamListUseCase: GetTeamListUseCase

    private val dummyTeams = TeamFactory.generateDummyGameList(12)


    @Before
    fun setup() {
        getTeamListUseCase = GetTeamListUseCase(teamRepository)
    }

    @Test
    fun buildUseCaseObservable_callRepository() {
        getTeamListUseCase.invoke()
        Mockito.verify(teamRepository).getTeams()
    }

    @Test
    fun getGames() = runTest {
        Mockito.`when`(teamRepository.getTeams()).thenReturn(
            dummyTeams
        )
        val response = getTeamListUseCase.invoke()
        Assert.assertEquals(dummyTeams.first(), response.first())
    }
}