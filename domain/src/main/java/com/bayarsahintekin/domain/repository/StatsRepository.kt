package com.bayarsahintekin.domain.repository

import com.bayarsahintekin.domain.entity.StatsEntity
import com.bayarsahintekin.domain.entity.StatsListEntity
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface StatsRepository {
    fun getStats(): Flow<List<StatsEntity>>
    suspend fun getAllStats(page: Int): Result<StatsListEntity>
}