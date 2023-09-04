package com.bayarsahintekin.data.local.games

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bayarsahintekin.data.entity.games.GameData
import com.bayarsahintekin.data.entity.games.GameDbData

@Dao
interface GameDao {
    @Query("SELECT * FROM games ORDER BY id")
    fun games(): PagingSource<Int, GameDbData>

    @Query("SELECT * FROM games")
    fun getGames(): List<GameDbData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveGames(games: List<GameDbData>)

    @Query("SELECT * FROM games WHERE id = :gameId")
    fun getGame(gameId: Int): GameDbData

    @Query("DELETE FROM games")
    fun clearGames()

}