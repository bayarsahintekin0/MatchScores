package com.bayarsahintekin.data.local.players

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bayarsahintekin.data.entity.players.PlayersDbData

@Dao
interface PlayerDao {
    @Query("SELECT * FROM players ORDER BY id")
    fun players(): PagingSource<Int, PlayersDbData>

    @Query("SELECT * FROM players")
    fun getPlayers(): List<PlayersDbData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePlayers(players: List<PlayersDbData>)

    @Query("SELECT * FROM players WHERE id = :playerId")
    fun getPlayer(playerId: String): PlayersDbData

    @Query("DELETE FROM players")
    fun clearPlayers()

}