package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.entity.stats.StatsListEntity
import com.bayarsahintekin.domain.repository.StatsRepository
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class StatsUseCase (
    private val statsRepository: StatsRepository
) {
    operator fun invoke(season: Int?, playerId: Int?) = statsRepository.getStatsByFilter(season, playerId)
}