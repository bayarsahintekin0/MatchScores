package com.bayarsahintekin.domain.usecase

import androidx.paging.PagingData
import com.bayarsahintekin.domain.entity.PlayerListEntity
import com.bayarsahintekin.domain.repository.PlayersRepository
import com.bayarsahintekin.domain.utils.Result

class PlayersUseCase(
    private val playersRepository: PlayersRepository
) {
    suspend operator fun invoke(page: Int): Result<PlayerListEntity> = playersRepository.getAllPlayers(page)

    fun getPlayers() = playersRepository.getPlayers()
}