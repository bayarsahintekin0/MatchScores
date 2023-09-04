package com.bayarsahintekin.data.repository.games

import com.bayarsahintekin.domain.entity.GameEntity
import com.bayarsahintekin.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow

class GameRepositoryImpl(
    private val remote: GameDataSource.Remote,
    private val local: GameDataSource.Local,
    private val remoteMediators: GameRemoteMediators
): GameRepository {

    override fun getGames(): Flow<List<GameEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllGames(page: Int) = remote.getGames(page)
}