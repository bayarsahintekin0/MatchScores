package com.bayarsahintekin.data.repository.games

import androidx.paging.PagingSource
import com.bayarsahintekin.data.entity.games.GameDbData

import com.bayarsahintekin.data.entity.games.GamesRemoteKeysDbData
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.entity.games.GameListEntity
import com.bayarsahintekin.domain.utils.Result

interface GameDataSource {

    interface Remote {
        suspend fun getGames(page: Int): Result<GameListEntity>
        suspend fun getFilterGames(page: Int,teamId: Int?,season: Int?): Result<GameListEntity>
        suspend fun getGameById(gameId: Int): Result<GameEntity>

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