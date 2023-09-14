package com.bayarsahintekin.domain.repository

import androidx.paging.PagingData
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import kotlinx.coroutines.flow.Flow

interface PlayersRepository {
    fun players(): Flow<PagingData<PlayerEntity>>
}