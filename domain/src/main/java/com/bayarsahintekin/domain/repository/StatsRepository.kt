package com.bayarsahintekin.domain.repository

import androidx.paging.PagingData
import androidx.paging.PagingState
import com.bayarsahintekin.domain.entity.stats.StatsEntity
import com.bayarsahintekin.domain.entity.stats.StatsListEntity
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface StatsRepository {
    fun getStatsByFilter(season: Int?, playerId: Int?): Flow<PagingData<StatsEntity>>
}