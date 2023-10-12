package com.bayarsahintekin.domain.usecase

import androidx.paging.PagingData
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.repository.PlayersRepository
import kotlinx.coroutines.flow.Flow

class SearchPlayerUseCase(
    private val playersRepository: PlayersRepository
) {
    operator fun invoke(query: String): Flow<PagingData<PlayerEntity>> = playersRepository.searchPlayer(query)
}