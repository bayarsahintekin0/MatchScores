package com.bayarsahintekin.data.repository

import androidx.paging.PagingSource
import com.bayarsahintekin.data.entity.players.PlayersDbData
import com.bayarsahintekin.data.entity.players.PlayersRemoteKeysDbData
import com.bayarsahintekin.domain.entity.PlayerEntity
import com.bayarsahintekin.domain.entity.PlayerListEntity
import com.bayarsahintekin.domain.utils.Result

interface PlayerDataSource {

    interface Remote {

        suspend fun getPlayers(page: Int):Result<PlayerListEntity>
        suspend fun getPlayers(movieIds: List<Int>): Result<List<PlayerEntity>>
        //suspend fun getPlayer(movieId: Int): Result<PlayerEntity>
        //suspend fun search(query: String, page: Int, limit: Int):Result<List<PlayerEntity>>

    }

    interface Local {
        fun players(): PagingSource<Int, PlayersDbData>
        suspend fun getPlayers(): Result<List<PlayerEntity>>
        suspend fun getPlayer(movieId: Int): Result<PlayerEntity>
        suspend fun savePlayers(movieEntities: List<PlayerEntity>)
        suspend fun getLastRemoteKey(): PlayersRemoteKeysDbData?
        suspend fun saveRemoteKey(key: PlayersRemoteKeysDbData)
        suspend fun clearPlayers()
        suspend fun clearRemoteKeys()
    }
}