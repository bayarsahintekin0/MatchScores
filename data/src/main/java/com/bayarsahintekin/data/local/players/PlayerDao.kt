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
    suspend fun savePlayers(players: List<PlayersDbData>)

    @Query("SELECT * FROM players WHERE id = :playerId")
    suspend fun getPlayer(playerId: Int): PlayersDbData?

    @Query("DELETE FROM players")
    suspend fun clearPlayers()

}