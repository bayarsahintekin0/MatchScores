package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.repository.TeamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class GetTeamListUseCase(
    private val teamRepository: TeamRepository
) {
    operator fun invoke() = teamRepository.getTeams().flowOn(Dispatchers.IO)
}