package com.bayarsahintekin.data.repository.games

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.bayarsahintekin.data.entity.games.toDomain
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class GameRepositoryImpl(
    private val remote: GameDataSource.Remote,
    private val local: GameDataSource.Local,
    private val remoteMediators: GameRemoteMediators
): GameRepository {
    override fun getGames():Flow<PagingData<GameEntity>>  = Pager(
        config = PagingConfig(
            pageSize = 25,
            enablePlaceholders = false
        ),
        remoteMediator = remoteMediators,
        pagingSourceFactory = { local.games() }
    ).flow.map { pagingData ->
        pagingData.map {
            it.toDomain()
        }
    }
}