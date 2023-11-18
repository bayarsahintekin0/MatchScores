package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.repository.GameRepository
import com.bayarsahintekin.domain.utils.Result

class GetGameByIdUseCase (
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(gameId: Int): Result<GameEntity> = gameRepository.getGameById(gameId)
}