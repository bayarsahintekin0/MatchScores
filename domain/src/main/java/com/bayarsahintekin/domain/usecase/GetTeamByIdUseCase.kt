package com.bayarsahintekin.domain.usecase

import androidx.paging.PagingData
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.repository.TeamRepository
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class GetTeamByIdUseCase (
    private val teamRepository: TeamRepository
) {
    suspend operator fun invoke(teamId: String):Result<TeamEntity> = teamRepository.getTeam(teamId)
}