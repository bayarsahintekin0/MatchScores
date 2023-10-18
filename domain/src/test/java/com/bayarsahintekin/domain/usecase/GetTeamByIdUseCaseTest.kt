package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.mock.TeamFactory
import com.bayarsahintekin.domain.repository.TeamRepository
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetTeamByIdUseCaseTest {

    @Mock
    lateinit var teamRepository: TeamRepository

    private lateinit var getTeamByIdUseCase: GetTeamByIdUseCase

    private val dummyTeam = TeamFactory.generateTeam()


    @Before
    fun setup() {
        getTeamByIdUseCase = GetTeamByIdUseCase(teamRepository)
    }

    @Test
    fun getPlayerById_Success() = runTest {
        Mockito.`when`(teamRepository.getTeam("1")).thenReturn(
            Result.Success(dummyTeam)
        )
        val result = getTeamByIdUseCase.invoke("1")
        Assert.assertEquals(Result.Success(dummyTeam), result)
    }

    @Test
    fun getPlayerById_Error() = runTest {
        val expectedResult = Result.Error<TeamEntity>(Throwable("error_test"))
        Mockito.`when`(teamRepository.getTeam("1")).thenReturn(
            expectedResult
        )
        val result = getTeamByIdUseCase.invoke("1")
        Assert.assertTrue(result is Result.Error && result.error.message == "error_test")
    }
}