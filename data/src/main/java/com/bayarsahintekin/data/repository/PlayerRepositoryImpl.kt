package com.bayarsahintekin.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.bayarsahintekin.data.entity.players.toDomain
import com.bayarsahintekin.domain.entity.PlayerEntity
import com.bayarsahintekin.domain.entity.PlayerListEntity
import com.bayarsahintekin.domain.repository.PlayersRepository
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class PlayerRepositoryImpl(
    private val remote: PlayerDataSource.Remote,
    private val local: PlayerDataSource.Local,
    private val remoteMediators: PlayersRemoteMediators
): PlayersRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPlayers(): Flow<List<PlayerEntity>> = flow {

    }


    override suspend fun getAllPlayers(page: Int): Result<PlayerListEntity> = remote.getPlayers(page)
}