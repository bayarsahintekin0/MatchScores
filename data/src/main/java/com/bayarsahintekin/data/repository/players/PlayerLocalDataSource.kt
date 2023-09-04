package com.bayarsahintekin.data.repository.players

import androidx.paging.PagingSource
import com.bayarsahintekin.data.entity.players.PlayersDbData
import com.bayarsahintekin.data.entity.players.PlayersRemoteKeysDbData
import com.bayarsahintekin.data.entity.players.toDomain
import com.bayarsahintekin.data.exception.DataNotAvailableException
import com.bayarsahintekin.data.local.players.PlayerDao
import com.bayarsahintekin.data.local.players.PlayersKeyDao
import com.bayarsahintekin.data.mapper.toDbData
import com.bayarsahintekin.data.utils.DiskExecutor
import com.bayarsahintekin.domain.entity.PlayerEntity
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext

class PlayerLocalDataSource(
    private val executor: DiskExecutor,
    private val playerDao: PlayerDao,
    private val playersKeyDao: PlayersKeyDao
): PlayerDataSource.Local {
    override fun players(): PagingSource<Int, PlayersDbData> = playerDao.players()

    override suspend fun getPlayers(): Result<List<PlayerEntity>> = withContext(executor.asCoroutineDispatcher()) {
        val players = playerDao.getPlayers()
        return@withContext if (players.isNotEmpty()) {
            Result.Success(players.map { it.toDomain() })
        } else {
            Result.Error(DataNotAvailableException())
        }
    }

    override suspend fun getPlayer(playerId: Int): Result<PlayerEntity> = withContext(executor.asCoroutineDispatcher()) {
        return@withContext playerDao.getPlayer(playerId = playerId)?.let {
            Result.Success(it.toDomain())
        } ?: Result.Error(DataNotAvailableException())
    }

    override suspend fun savePlayers(playerEntities: List<PlayerEntity>) = withContext(executor.asCoroutineDispatcher()) {
        playerDao.savePlayers(playerEntities.map { it.toDbData() })
    }

    override suspend fun getLastRemoteKey(): PlayersRemoteKeysDbData? = withContext(executor.asCoroutineDispatcher()) {
        playersKeyDao.getLastRemoteKey()
    }

    override suspend fun saveRemoteKey(key: PlayersRemoteKeysDbData) = withContext(executor.asCoroutineDispatcher()) {
        playersKeyDao.saveRemoteKey(key)
    }

    override suspend fun clearPlayers() = withContext(executor.asCoroutineDispatcher()) {
        playerDao.clearPlayers()
    }

    override suspend fun clearRemoteKeys() = withContext(executor.asCoroutineDispatcher()) {
        playersKeyDao.clearRemoteKeys()
    }


}