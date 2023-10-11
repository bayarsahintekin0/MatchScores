package com.bayarsahintekin.domain.repository

import androidx.paging.PagingData
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface PlayersRepository {
    fun players(): Flow<PagingData<PlayerEntity>>
    suspend fun getPlayerById(id: String): Result<PlayerEntity>
}