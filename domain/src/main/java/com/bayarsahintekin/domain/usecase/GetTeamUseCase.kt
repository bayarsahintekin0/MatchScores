package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.repository.TeamRepository
import com.bayarsahintekin.domain.utils.Result

class GetTeamUseCase(
    private val teamRepository: TeamRepository
) {
    suspend operator fun invoke(teamId: String): Result<TeamEntity> = teamRepository.getTeam(teamId)
}