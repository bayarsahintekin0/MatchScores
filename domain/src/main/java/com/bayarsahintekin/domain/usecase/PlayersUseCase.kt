package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.entity.PlayerListEntity
import com.bayarsahintekin.domain.repository.PlayersRepository
import com.bayarsahintekin.domain.utils.Result

class PlayersUseCase(
    private val playersRepository: PlayersRepository
) {
    suspend operator fun invoke(page: Int): Result<PlayerListEntity> = playersRepository.getAllPlayers(page)

    suspend fun getPlayers(page: Int) = playersRepository.getPlayers(20)
}