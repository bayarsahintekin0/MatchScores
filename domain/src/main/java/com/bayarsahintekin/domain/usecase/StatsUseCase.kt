package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.entity.GameListEntity
import com.bayarsahintekin.domain.entity.StatsListEntity
import com.bayarsahintekin.domain.repository.GameRepository
import com.bayarsahintekin.domain.repository.StatsRepository
import com.bayarsahintekin.domain.utils.Result

class StatsUseCase (
    private val statsRepository: StatsRepository
) {
    suspend operator fun invoke(page: Int): Result<StatsListEntity> = statsRepository.getAllStats(page)

    fun getStats() = statsRepository.getStats()
}