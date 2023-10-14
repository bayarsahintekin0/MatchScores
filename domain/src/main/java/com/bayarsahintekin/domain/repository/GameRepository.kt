package com.bayarsahintekin.domain.repository

import androidx.paging.PagingData
import com.bayarsahintekin.domain.entity.games.GameEntity
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGames(): Flow<PagingData<GameEntity>>
    fun getFilterGames(teamId: Int?, season: Int?): Flow<PagingData<GameEntity>>
}