package com.bayarsahintekin.data.repository.stats

import androidx.paging.PagingSource
import com.bayarsahintekin.data.entity.stats.StatsDbData
import com.bayarsahintekin.data.entity.stats.StatsRemoteKeysDbData
import com.bayarsahintekin.domain.entity.stats.StatsEntity
import com.bayarsahintekin.domain.entity.stats.StatsListEntity
import com.bayarsahintekin.domain.utils.Result

interface StatsDataSource {
    interface Remote {
        suspend fun getStats(page: Int): Result<StatsListEntity>
        suspend fun getStats(idList: List<Int>): Result<List<StatsEntity>>
        //suspend fun getPlayer(movieId: Int): Result<PlayerEntity>
        //suspend fun search(query: String, page: Int, limit: Int):Result<List<PlayerEntity>>

    }

    interface Local {
        fun stats(): PagingSource<Int, StatsDbData>
        suspend fun getStats(): Result<List<StatsEntity>>
        suspend fun getStat(statId: Int): Result<StatsEntity>
        suspend fun saveStats(statEntities: List<StatsEntity>)
        suspend fun getLastRemoteKey(): StatsRemoteKeysDbData?
        suspend fun saveRemoteKey(key: StatsRemoteKeysDbData)
        suspend fun clearStats()
        suspend fun clearRemoteKeys()
    }
}