package com.bayarsahintekin.data.repository.games

import androidx.paging.PagingSource
import com.bayarsahintekin.data.entity.games.GameDbData
import com.bayarsahintekin.data.entity.games.GamesRemoteKeysDbData
import com.bayarsahintekin.data.entity.games.toDomain
import com.bayarsahintekin.data.exception.DataNotAvailableException
import com.bayarsahintekin.data.local.games.GameDao
import com.bayarsahintekin.data.local.games.GameRemoteKeysDao
import com.bayarsahintekin.data.mapper.toDbData
import com.bayarsahintekin.data.utils.DiskExecutor
import com.bayarsahintekin.domain.entity.GameEntity
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext

class GameLocalDataSource (
    private val executor: DiskExecutor,
    private val gameDao: GameDao,
    private val gameKeyDao: GameRemoteKeysDao
): GameDataSource.Local {
    override fun games(): PagingSource<Int, GameDbData> = gameDao.games()

    override suspend fun getGames(): Result<List<GameEntity>> = withContext(executor.asCoroutineDispatcher()) {
        val games = gameDao.getGames()
        return@withContext if (games.isNotEmpty()) {
            Result.Success(games.map { it.toDomain() })
        } else {
            Result.Error(DataNotAvailableException())
        }
    }

    override suspend fun getGame(gameId: Int): Result<GameEntity> = withContext(executor.asCoroutineDispatcher()) {
        return@withContext gameDao.getGame(gameId = gameId).let {
            Result.Success(it.toDomain())
        } ?: Result.Error(DataNotAvailableException())
    }

    override suspend fun saveGames(gameEntities: List<GameEntity>) {
        gameDao.saveGames(gameEntities.map { it.toDbData() })
    }

    override suspend fun getLastRemoteKey(): GamesRemoteKeysDbData? = withContext(executor.asCoroutineDispatcher()) {
        gameKeyDao.getLastRemoteKey()
    }

    override suspend fun saveRemoteKey(key: GamesRemoteKeysDbData) = withContext(executor.asCoroutineDispatcher()) {
        gameKeyDao.saveRemoteKey(key)
    }

    override suspend fun clearGames() {
        gameDao.clearGames()
    }

    override suspend fun clearRemoteKeys() = withContext(executor.asCoroutineDispatcher()) {
        gameKeyDao.clearRemoteKeys()
    }


}