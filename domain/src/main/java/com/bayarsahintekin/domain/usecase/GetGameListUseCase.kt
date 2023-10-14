package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class GetGameListUseCase (
    private val gameRepository: GameRepository
) {
    fun getGames() = gameRepository.getGames().flowOn(Dispatchers.IO)
}