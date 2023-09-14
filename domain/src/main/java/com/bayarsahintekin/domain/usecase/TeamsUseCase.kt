package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.entity.teams.TeamListEntity
import com.bayarsahintekin.domain.repository.TeamRepository
import com.bayarsahintekin.domain.utils.Result

class TeamsUseCase(
    private val teamRepository: TeamRepository
) {
    suspend operator fun invoke(): Result<TeamListEntity> = teamRepository.getTeams()
}