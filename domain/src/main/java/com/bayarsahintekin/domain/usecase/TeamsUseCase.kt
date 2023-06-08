package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.entity.ListResponseEntity
import com.bayarsahintekin.domain.repository.TeamRepository
import com.bayarsahintekin.domain.utils.Result

class TeamsUseCase(
    private val teamRepository: TeamRepository
) {
    suspend operator fun invoke(): Result<ListResponseEntity> = teamRepository.getTeams()
}