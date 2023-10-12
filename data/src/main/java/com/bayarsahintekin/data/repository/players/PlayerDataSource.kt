package com.bayarsahintekin.data.repository.players

import androidx.paging.PagingSource
import com.bayarsahintekin.data.entity.players.PlayersDbData
import com.bayarsahintekin.data.entity.players.PlayersRemoteKeysDbData
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.entity.players.PlayerListEntity
import com.bayarsahintekin.domain.utils.Result

interface PlayerDataSource {

    interface Remote {

        suspend fun getPlayers(page: Int):Result<PlayerListEntity>
        suspend fun getPlayer(playerId: String): Result<PlayerEntity>
        suspend fun search(query: String, page: Int):Result<PlayerListEntity>

    }

    interface Local {
        fun players(): PagingSource<Int, PlayersDbData>
        suspend fun getPlayers(): Result<List<PlayerEntity>>
        suspend fun getPlayer(playerId: String): Result<PlayerEntity>
        suspend fun savePlayers(playerEntities: List<PlayerEntity>)
        suspend fun getLastRemoteKey(): PlayersRemoteKeysDbData?
        suspend fun saveRemoteKey(key: PlayersRemoteKeysDbData)
        suspend fun clearPlayers()
        suspend fun clearRemoteKeys()
    }
}