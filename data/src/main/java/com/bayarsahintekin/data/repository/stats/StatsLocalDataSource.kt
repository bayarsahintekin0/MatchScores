package com.bayarsahintekin.data.repository.stats

import androidx.paging.PagingSource
import com.bayarsahintekin.data.entity.stats.StatsDbData
import com.bayarsahintekin.data.entity.stats.StatsRemoteKeysDbData
import com.bayarsahintekin.data.entity.stats.toDomain
import com.bayarsahintekin.data.exception.DataNotAvailableException
import com.bayarsahintekin.data.local.stats.StatsDao
import com.bayarsahintekin.data.local.stats.StatsRemoteKeysDao
import com.bayarsahintekin.data.mapper.toDbData
import com.bayarsahintekin.data.utils.DiskExecutor
import com.bayarsahintekin.domain.entity.stats.StatsEntity
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext

class StatsLocalDataSource (
    private val executor: DiskExecutor,
    private val statsDao: StatsDao,
    private val statsRemoteKeysDao: StatsRemoteKeysDao
): StatsDataSource.Local {

    override fun stats(): PagingSource<Int, StatsDbData>  = statsDao.stats()

    override suspend fun getStats(): Result<List<StatsEntity>> = withContext(executor.asCoroutineDispatcher()) {
        val stats = statsDao.getStats()
        return@withContext if (stats.isEmpty()) {
            Result.Success(stats.map { it.toDomain() })
        } else {
            Result.Error(DataNotAvailableException())
        }
    }

    override suspend fun getStat(statId: Int): Result<StatsEntity> = withContext(executor.asCoroutineDispatcher()) {
        return@withContext statsDao.getStat(statsId = statId).let {
            Result.Success(it.toDomain())
        } ?: Result.Error(DataNotAvailableException())
    }

    override suspend fun saveStats(statEntities: List<StatsEntity>) {
        statsDao.saveStats(statEntities.map { it.toDbData() })
    }

    override suspend fun getLastRemoteKey(): StatsRemoteKeysDbData? = withContext(executor.asCoroutineDispatcher()) {
        statsRemoteKeysDao.getLastRemoteKey()
    }

    override suspend fun saveRemoteKey(key: StatsRemoteKeysDbData) {
        statsRemoteKeysDao.saveRemoteKey(key)
    }

    override suspend fun clearStats() {
        statsDao.clearStats()
    }

    override suspend fun clearRemoteKeys() = withContext(executor.asCoroutineDispatcher()) {
        statsRemoteKeysDao.clearRemoteKeys()
    }


}