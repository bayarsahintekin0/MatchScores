package com.bayarsahintekin.data.repository.players

import com.bayarsahintekin.data.entity.players.PlayerListData
import com.bayarsahintekin.data.entity.players.toDomain
import com.bayarsahintekin.data.remote.ScoreServices
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.entity.players.PlayerListEntity
import com.bayarsahintekin.domain.utils.Result

class PlayerRemoteDataSource(
    private val scoreService: ScoreServices
) : PlayerDataSource.Remote {

    override suspend fun getPlayers(page: Int): Result<PlayerListEntity> =
        try {
            val result = scoreService.getAllPlayers(page)
            Result.Success(
                PlayerListData(
                    data = result.data,
                    meta = result.meta
                ).toDomain()
            )
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun getPlayer(playerId: String): Result<PlayerEntity> =
        try {
            val result = scoreService.getPlayerById(playerId)
            Result.Success(result.toDomain())
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun search(query: String, page: Int): Result<PlayerListEntity> =
        try {
            val result = scoreService.searchPlayers(page,query)
            Result.Success(
                PlayerListData(
                    data = result.data,
                    meta = result.meta
                ).toDomain()
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
}