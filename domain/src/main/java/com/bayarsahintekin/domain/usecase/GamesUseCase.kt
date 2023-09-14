package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.entity.games.GameListEntity
import com.bayarsahintekin.domain.repository.GameRepository
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class GamesUseCase (
    private val gameRepository: GameRepository
) {
    fun getGames() = gameRepository.getGames().flowOn(Dispatchers.IO)
}