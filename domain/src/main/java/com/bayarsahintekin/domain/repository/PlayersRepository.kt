package com.bayarsahintekin.domain.repository

import androidx.paging.PagingData
import com.bayarsahintekin.domain.entity.PlayerEntity
import com.bayarsahintekin.domain.entity.PlayerListEntity
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface PlayersRepository {

    suspend fun getPlayers(pageSize: Int): Flow<PagingData<PlayerEntity>>
    suspend fun getAllPlayers(page: Int): Result<PlayerListEntity>
}