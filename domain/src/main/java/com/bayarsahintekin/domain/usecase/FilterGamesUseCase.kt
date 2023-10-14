package com.bayarsahintekin.domain.usecase

import androidx.paging.PagingData
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow

class FilterGamesUseCase(
    private val gameRepository: GameRepository
) {
    operator fun invoke(teamId: Int?,season: Int?): Flow<PagingData<GameEntity>> = gameRepository.getFilterGames(teamId, season)
}