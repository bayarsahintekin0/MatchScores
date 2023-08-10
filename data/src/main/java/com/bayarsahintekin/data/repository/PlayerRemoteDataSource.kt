package com.bayarsahintekin.data.repository

import com.bayarsahintekin.data.entity.players.PlayerListData
import com.bayarsahintekin.data.entity.players.toDomain
import com.bayarsahintekin.data.remote.ScoreServices
import com.bayarsahintekin.domain.entity.PlayerListEntity
import com.bayarsahintekin.domain.utils.Result

class PlayerRemoteDataSource (
    private val scoreService: ScoreServices
) : PlayerDataSource.Remote {

    override suspend fun getAllPlayers(): Result<PlayerListEntity> =
        try {
            val result = scoreService.getAllPlayers()
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