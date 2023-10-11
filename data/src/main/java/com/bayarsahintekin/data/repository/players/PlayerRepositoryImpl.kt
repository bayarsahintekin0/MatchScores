package com.bayarsahintekin.data.repository.players

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.bayarsahintekin.data.entity.players.toDomain
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.repository.PlayersRepository
import com.bayarsahintekin.domain.utils.Result
import com.bayarsahintekin.domain.utils.onError
import com.bayarsahintekin.domain.utils.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlayerRepositoryImpl(
    private val remote: PlayerDataSource.Remote,
    private val local: PlayerDataSource.Local,
    private val remoteMediators: PlayerRemoteMediators
): PlayersRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun players(): Flow<PagingData<PlayerEntity>>  = Pager(
    config = PagingConfig(
    pageSize = 25,
    enablePlaceholders = false
    ),
    remoteMediator = remoteMediators,
    pagingSourceFactory = { local.players() }
    ).flow.map { pagingData ->
        pagingData.map {
            it.toDomain()
        }
    }

    override suspend fun getPlayerById(id: String): Result<PlayerEntity> = local.getPlayer(id).onSuccess {
        it
    }.onError {
        remote.getPlayer(id)
    }

}