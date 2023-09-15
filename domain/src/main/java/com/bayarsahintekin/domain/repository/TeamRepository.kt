package com.bayarsahintekin.domain.repository

import androidx.paging.PagingData
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.entity.teams.TeamListEntity
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface TeamRepository {

    fun getTeams(): Flow<PagingData<TeamEntity>>

    suspend fun getTeam(id: String): Result<TeamEntity>
}