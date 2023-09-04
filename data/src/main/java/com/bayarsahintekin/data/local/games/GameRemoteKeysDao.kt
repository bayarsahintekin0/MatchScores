package com.bayarsahintekin.data.local.games

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bayarsahintekin.data.entity.games.GamesRemoteKeysDbData

@Dao
interface GameRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRemoteKey(keys: GamesRemoteKeysDbData)

    @Query("SELECT * FROM games_remote_keys WHERE id=:id")
    fun getRemoteKeyByGameId(id: Int): GamesRemoteKeysDbData?

    @Query("DELETE FROM games_remote_keys")
    fun clearRemoteKeys()

    @Query("SELECT * FROM games_remote_keys WHERE id = (SELECT MAX(id) FROM games_remote_keys)")
    fun getLastRemoteKey(): GamesRemoteKeysDbData?

}