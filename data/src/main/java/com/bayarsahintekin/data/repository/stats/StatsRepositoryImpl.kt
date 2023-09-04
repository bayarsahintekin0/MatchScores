package com.bayarsahintekin.data.repository.stats

import com.bayarsahintekin.domain.entity.GameEntity
import com.bayarsahintekin.domain.entity.StatsEntity
import com.bayarsahintekin.domain.repository.GameRepository
import com.bayarsahintekin.domain.repository.StatsRepository
import kotlinx.coroutines.flow.Flow

class StatsRepositoryImpl(
    private val remote: StatsDataSource.Remote,
    private val local: StatsDataSource.Local,
    private val remoteMediators: StatsRemoteMediators
): StatsRepository {

    override fun getStats(): Flow<List<StatsEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllStats(page: Int) = remote.getStats(page)
}