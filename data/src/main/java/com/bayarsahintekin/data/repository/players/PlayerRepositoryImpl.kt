package com.bayarsahintekin.data.repository.players

import androidx.paging.ExperimentalPagingApi
import com.bayarsahintekin.domain.entity.PlayerEntity
import com.bayarsahintekin.domain.entity.PlayerListEntity
import com.bayarsahintekin.domain.repository.PlayersRepository
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlayerRepositoryImpl(
    private val remote: PlayerDataSource.Remote,
    private val local: PlayerDataSource.Local,
    private val remoteMediators: PlayerRemoteMediators
): PlayersRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPlayers(): Flow<List<PlayerEntity>> = flow {

    }


    override suspend fun getAllPlayers(page: Int): Result<PlayerListEntity> = remote.getPlayers(page)
}