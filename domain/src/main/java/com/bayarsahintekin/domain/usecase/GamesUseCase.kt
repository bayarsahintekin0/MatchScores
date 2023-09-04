package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.entity.GameListEntity
import com.bayarsahintekin.domain.repository.GameRepository
import com.bayarsahintekin.domain.utils.Result

class GamesUseCase (
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(page: Int): Result<GameListEntity> = gameRepository.getAllGames(page)

    fun getGames() = gameRepository.getGames()
}