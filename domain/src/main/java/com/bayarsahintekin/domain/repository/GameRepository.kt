package com.bayarsahintekin.domain.repository

import com.bayarsahintekin.domain.entity.GameEntity
import com.bayarsahintekin.domain.entity.GameListEntity
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGames(): Flow<List<GameEntity>>
    suspend fun getAllGames(page: Int): Result<GameListEntity>
}