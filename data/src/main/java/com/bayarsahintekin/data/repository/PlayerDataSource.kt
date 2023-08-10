package com.bayarsahintekin.data.repository

import com.bayarsahintekin.domain.entity.PlayerListEntity
import com.bayarsahintekin.domain.utils.Result

interface PlayerDataSource {

    interface Remote {
        suspend fun getAllPlayers(): Result<PlayerListEntity>
    }
}