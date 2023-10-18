package com.bayarsahintekin.domain.usecase

import com.bayarsahintekin.domain.repository.PlayersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class GetPlayerListUseCase(
    private val playersRepository: PlayersRepository
) {
    operator fun invoke() = playersRepository.players().flowOn(Dispatchers.IO)
}