package com.bayarsahintekin.domain.mock

import androidx.paging.PagingData
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object TeamFactory {

    fun generateDummyGameList(size: Int): Flow<PagingData<TeamEntity>> {
        val teamList = mutableListOf<TeamEntity>()
        repeat(size) {
            teamList.add(generateTeam())
        }
        return convertToPagingDataList(teamList)
    }

    private fun convertToPagingDataList(inputList: List<TeamEntity>): Flow<PagingData<TeamEntity>> {
        val pagingDataList: PagingData<TeamEntity> = PagingData.from(inputList)
        return toFlow(pagingDataList)
    }

    private fun toFlow(data: PagingData<TeamEntity>): Flow<PagingData<TeamEntity>> =
        flow { emit(data) }

    fun generateTeam(): TeamEntity {
        return TeamEntity(
            id = DataFactory.getRandomInt(),
            abbreviation = DataFactory.getRandomString(),
            city = DataFactory.getRandomString(),
            conference = DataFactory.getRandomString(),
            division = DataFactory.getRandomString(),
            fullName = DataFactory.getRandomString(),
            name = DataFactory.getRandomString()
        )
    }
}