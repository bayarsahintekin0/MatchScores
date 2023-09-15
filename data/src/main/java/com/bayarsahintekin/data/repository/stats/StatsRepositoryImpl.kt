package com.bayarsahintekin.data.repository.stats

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.bayarsahintekin.data.entity.players.toDomain
import com.bayarsahintekin.data.entity.stats.toDomain
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.entity.stats.StatsEntity
import com.bayarsahintekin.domain.repository.StatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StatsRepositoryImpl(
    private val remote: StatsDataSource.Remote,
    private val local: StatsDataSource.Local,
    private val remoteMediators: StatsRemoteMediators
): StatsRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getStats(): Flow<PagingData<StatsEntity>>  = Pager(
        config = PagingConfig(
            pageSize = 25,
            enablePlaceholders = false
        ),
        remoteMediator = remoteMediators,
        pagingSourceFactory = { local.stats() }
    ).flow.map { pagingData ->
        pagingData.map {
            it.toDomain()
        }
    }
}