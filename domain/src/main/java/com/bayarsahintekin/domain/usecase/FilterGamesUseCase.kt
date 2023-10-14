package com.bayarsahintekin.domain.usecase

import androidx.paging.PagingData
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow

class FilterGamesUseCase(
    private val gameRepository: GameRepository
) {
    operator fun invoke(teamIdList: List<Int>,seasons: List<Int>): Flow<PagingData<GameEntity>> = gameRepository.getFilterGames(teamIdList, seasons)
}