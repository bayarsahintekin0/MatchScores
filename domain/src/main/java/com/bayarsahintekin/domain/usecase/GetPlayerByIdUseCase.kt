package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.repository.PlayersRepository

class GetPlayerByIdUseCase (
    private val playersRepository: PlayersRepository
) {
    suspend operator fun invoke(playerId: String) = playersRepository.getPlayerById(playerId)
}