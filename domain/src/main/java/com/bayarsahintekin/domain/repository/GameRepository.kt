package com.bayarsahintekin.domain.repository

import androidx.paging.PagingData
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGames(): Flow<PagingData<GameEntity>>
    fun getFilterGames(teamId: Int?, season: Int?): Flow<PagingData<GameEntity>>
    suspend fun getGameById(gameId: Int): Result<GameEntity>
}