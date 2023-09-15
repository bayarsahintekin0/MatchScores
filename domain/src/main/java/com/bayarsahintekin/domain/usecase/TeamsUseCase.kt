package com.bayarsahintekin.domain.usecase

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.entity.teams.TeamListEntity
import com.bayarsahintekin.domain.repository.TeamRepository
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class TeamsUseCase(
    private val teamRepository: TeamRepository
) {
    fun getTeams() = teamRepository.getTeams().flowOn(Dispatchers.IO)
}