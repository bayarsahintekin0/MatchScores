package com.bayarsahintekin.data.repository.games

import androidx.paging.PagingSource
import com.bayarsahintekin.data.entity.games.GameDbData

import com.bayarsahintekin.data.entity.games.GamesRemoteKeysDbData
import com.bayarsahintekin.domain.entity.GameEntity
import com.bayarsahintekin.domain.entity.GameListEntity
import com.bayarsahintekin.domain.utils.Result

interface GameDataSource {

    interface Remote {
        suspend fun getGames(page: Int): Result<GameListEntity>
        suspend fun getGames(idLid: List<Int>): Result<List<GameEntity>>
        //suspend fun getPlayer(movieId: Int): Result<PlayerEntity>
        //suspend fun search(query: String, page: Int, limit: Int):Result<List<PlayerEntity>>

    }

    interface Local {
        fun games(): PagingSource<Int, GameDbData>
        suspend fun getGames(): Result<List<GameEntity>>
        suspend fun getGame(gameId: Int): Result<GameEntity>
        suspend fun saveGames(gameEntities: List<GameEntity>)
        suspend fun getLastRemoteKey(): GamesRemoteKeysDbData?
        suspend fun saveRemoteKey(key: GamesRemoteKeysDbData)
        suspend fun clearGames()
        suspend fun clearRemoteKeys()
    }
}