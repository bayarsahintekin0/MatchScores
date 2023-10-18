package com.bayarsahintekin.domain.mock

import androidx.paging.PagingData
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData.*
import androidx.paging.PagingData.Companion.from
import androidx.paging.map
import kotlinx.coroutines.flow.flow

object GamesFactory {
    fun generateDummyGameList(size: Int): Flow<PagingData<GameEntity>> {
        val gameList = mutableListOf<GameEntity>()
        repeat(size) {
            gameList.add(generateGame())
        }
        return convertToPagingDataList(gameList)
    }

    private fun convertToPagingDataList(inputList: List<GameEntity>): Flow<PagingData<GameEntity>> {
        val pagingDataList: PagingData<GameEntity> = from(inputList)
        return toFlow(pagingDataList)
    }

    private fun toFlow(data: PagingData<GameEntity>): Flow<PagingData<GameEntity>> = flow { emit(data) }

    private fun generateGame(): GameEntity {
        return GameEntity(
            id = DataFactory.getRandomInt(),
            date = DataFactory.getRandomString(),
            homeTeam = TeamEntity(
                id = DataFactory.getRandomInt(),
                abbreviation = DataFactory.getRandomString(),
                city = DataFactory.getRandomString(),
                conference = DataFactory.getRandomString(),
                division = DataFactory.getRandomString(),
                fullName = DataFactory.getRandomString(),
                name = DataFactory.getRandomString()
            ),
            homeTeamScore = DataFactory.getRandomInt(),
            period = DataFactory.getRandomInt(),
            postseason = DataFactory.getRandomBoolean(),
            season = DataFactory.getRandomInt(),
            status = DataFactory.getRandomString(),
            time = DataFactory.getRandomString(),
            visitorTeam = TeamEntity(
                id = DataFactory.getRandomInt(),
                abbreviation = DataFactory.getRandomString(),
                city = DataFactory.getRandomString(),
                conference = DataFactory.getRandomString(),
                division = DataFactory.getRandomString(),
                fullName = DataFactory.getRandomString(),
                name = DataFactory.getRandomString()
            ),
            visitorTeamScore = DataFactory.getRandomInt()
        )
    }
}