package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.repository.PlayersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class PlayersUseCase(
    private val playersRepository: PlayersRepository
) { fun players() = playersRepository.players().flowOn(Dispatchers.IO)
}