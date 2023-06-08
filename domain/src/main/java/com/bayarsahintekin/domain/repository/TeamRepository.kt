package com.bayarsahintekin.domain.repository

import com.bayarsahintekin.domain.entity.ListResponseEntity
import com.bayarsahintekin.domain.entity.TeamEntity
import com.bayarsahintekin.domain.utils.Result

interface TeamRepository {

    suspend fun getTeams(): Result<ListResponseEntity>
}