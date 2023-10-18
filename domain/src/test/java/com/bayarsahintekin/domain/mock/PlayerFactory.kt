package com.bayarsahintekin.domain.mock

import androidx.paging.PagingData
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object PlayerFactory {
    fun generateDummyPlayerList(size: Int): Flow<PagingData<PlayerEntity>> {
        val playerList = mutableListOf<PlayerEntity>()
        repeat(size) {
            playerList.add(generatePlayer())
        }
        return convertToPagingDataList(playerList)
    }

    private fun convertToPagingDataList(inputList: List<PlayerEntity>): Flow<PagingData<PlayerEntity>> {
        val pagingDataList: PagingData<PlayerEntity> = PagingData.from(inputList)
        return toFlow(pagingDataList)
    }

    private fun toFlow(data: PagingData<PlayerEntity>): Flow<PagingData<PlayerEntity>> = flow { emit(data) }

    fun generatePlayer() = PlayerEntity(
        id = DataFactory.getRandomInt(),
        firstName = DataFactory.getRandomString(),
        heightFeet = DataFactory.getRandomString(),
        heightInches = DataFactory.getRandomString(),
        lastName = DataFactory.getRandomString(),
        position = DataFactory.getRandomString(),
        team = TeamEntity(
            id = DataFactory.getRandomInt(),
            abbreviation = DataFactory.getRandomString(),
            city = DataFactory.getRandomString(),
            conference = DataFactory.getRandomString(),
            division = DataFactory.getRandomString(),
            fullName = DataFactory.getRandomString(),
            name = DataFactory.getRandomString()
        ),
        weightPounds = DataFactory.getRandomString()
    )
}