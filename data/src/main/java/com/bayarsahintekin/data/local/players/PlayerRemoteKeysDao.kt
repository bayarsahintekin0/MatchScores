package com.bayarsahintekin.data.local.players

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bayarsahintekin.data.entity.players.PlayersRemoteKeysDbData


@Dao
interface PlayersKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun saveRemoteKey(keys: PlayersRemoteKeysDbData)

    @Query("SELECT * FROM players_remote_keys WHERE id=:id")
     fun getRemoteKeyByMovieId(id: Int): PlayersRemoteKeysDbData?

    @Query("DELETE FROM players_remote_keys")
     fun clearRemoteKeys()

    @Query("SELECT * FROM players_remote_keys WHERE id = (SELECT MAX(id) FROM players_remote_keys)")
     fun getLastRemoteKey(): PlayersRemoteKeysDbData?

}