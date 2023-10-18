package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.mock.StatFactory
import com.bayarsahintekin.domain.repository.GameRepository
import com.bayarsahintekin.domain.repository.StatsRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetStatsByFilterUseCaseTest {

    @Mock
    lateinit var statsRepository: StatsRepository

    private lateinit var getStatsByFilterUseCaseTest: GetStatsByFilterUseCase

    private val dummyStat = StatFactory.generateDummyStatList(12)

    @Before
    fun setup() {
        getStatsByFilterUseCaseTest = GetStatsByFilterUseCase(statsRepository)
    }

    @Test
    fun buildUseCaseObservable_callRepository() {
        getStatsByFilterUseCaseTest.invoke(2020,1)

        Mockito.verify(statsRepository).getStatsByFilter(2020,1)
    }

    @Test
    fun getStatsWithFullParameter() {
        Mockito.`when`(statsRepository.getStatsByFilter(2020, 1)).thenReturn(
            dummyStat
        )
        val response = getStatsByFilterUseCaseTest.invoke(2020,1)
        Assert.assertEquals(dummyStat,response)
    }

    @Test
    fun getStatsWithOnlyTeamId() {
        Mockito.`when`(statsRepository.getStatsByFilter(2020, null)).thenReturn(
            dummyStat
        )
        val  response = getStatsByFilterUseCaseTest.invoke(2020,null)
        Assert.assertEquals(dummyStat,response)
    }

    @Test
    fun getStatsWithOnlySeason() {
        Mockito.`when`(statsRepository.getStatsByFilter(null, 1)).thenReturn(
            dummyStat
        )
        val  response = getStatsByFilterUseCaseTest.invoke(null,1)
        Assert.assertEquals(dummyStat,response)
    }

    @Test
    fun getStatsWithNoParameter() {
        Mockito.`when`(statsRepository.getStatsByFilter(null, null)).thenReturn(
            dummyStat
        )
        val  response = getStatsByFilterUseCaseTest.invoke(null,null)
        Assert.assertEquals(dummyStat,response)
    }

}