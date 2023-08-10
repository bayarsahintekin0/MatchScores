package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.entity.PlayerListEntity
import com.bayarsahintekin.domain.repository.PlayersRepository
import com.bayarsahintekin.domain.utils.Result

class PlayersUseCase(
    private val playersRepository: PlayersRepository
) {
    suspend operator fun invoke(): Result<PlayerListEntity> = playersRepository.getAllPlayers()
}