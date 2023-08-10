package com.bayarsahintekin.data.repository

import com.bayarsahintekin.domain.entity.PlayerListEntity
import com.bayarsahintekin.domain.repository.PlayersRepository
import com.bayarsahintekin.domain.utils.Result

class PlayerRepositoryImpl(
    private val remote: PlayerDataSource.Remote
): PlayersRepository {

    override suspend fun getAllPlayers(): Result<PlayerListEntity> = remote.getAllPlayers()
}