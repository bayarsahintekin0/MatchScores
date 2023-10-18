package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.repository.StatsRepository

class GetStatsByFilterUseCase (
    private val statsRepository: StatsRepository
) {
    operator fun invoke(season: Int?, playerId: Int?) = statsRepository.getStatsByFilter(season, playerId)
}