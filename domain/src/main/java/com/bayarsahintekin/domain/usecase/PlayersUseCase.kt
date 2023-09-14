package com.bayarsahintekin.domain.usecase

import androidx.paging.PagingData
import com.bayarsahintekin.domain.entity.PlayerListEntity
import com.bayarsahintekin.domain.repository.PlayersRepository
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class PlayersUseCase(
    private val playersRepository: PlayersRepository
) {
    suspend operator fun invoke(page: Int): Result<PlayerListEntity> = playersRepository.getAllPlayers(page)

    fun players() = playersRepository.players().flowOn(Dispatchers.IO)
}