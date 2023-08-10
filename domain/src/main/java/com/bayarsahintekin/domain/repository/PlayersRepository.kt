package com.bayarsahintekin.domain.repository

import com.bayarsahintekin.domain.entity.PlayerListEntity
import com.bayarsahintekin.domain.utils.Result

interface PlayersRepository {
    suspend fun getAllPlayers(): Result<PlayerListEntity>
}