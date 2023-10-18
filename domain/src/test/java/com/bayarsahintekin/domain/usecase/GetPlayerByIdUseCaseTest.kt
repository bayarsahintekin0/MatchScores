package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.mock.PlayerFactory
import com.bayarsahintekin.domain.repository.PlayersRepository
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetPlayerByIdUseCaseTest {

    @Mock
    lateinit var playerRepository: PlayersRepository

    private lateinit var getPlayerByIdUseCase: GetPlayerByIdUseCase

    private val dummyPlayer = PlayerFactory.generatePlayer()


    @Before
    fun setup(){
        getPlayerByIdUseCase = GetPlayerByIdUseCase(playerRepository)
    }

    @Test
    fun getPlayerById_Success() = runTest {
        `when`(playerRepository.getPlayerById("1")).thenReturn(
            Result.Success(dummyPlayer)
        )
        val result = getPlayerByIdUseCase.invoke("1")
        assertEquals(Result.Success(dummyPlayer),result)
    }

    @Test
    fun getPlayerById_Error() = runTest {
        val expectedResult = Result.Error<PlayerEntity>(Throwable("error_test"))
        `when`(playerRepository.getPlayerById("1")).thenReturn(
            expectedResult
        )
        val result = getPlayerByIdUseCase.invoke("1")
        Assert.assertTrue(result is Result.Error && result.error.message == "error_test")
    }

}