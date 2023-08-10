package com.bayarsahintekin.domain.repository

import com.bayarsahintekin.domain.entity.TeamEntity
import com.bayarsahintekin.domain.entity.TeamListEntity
import com.bayarsahintekin.domain.utils.Result

interface TeamRepository {

    suspend fun getTeams(): Result<TeamListEntity>

    suspend fun getTeam(id: String): Result<TeamEntity>
}