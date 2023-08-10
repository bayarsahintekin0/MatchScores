package com.bayarsahintekin.data.local.teams

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bayarsahintekin.data.entity.teams.TeamsKeyDbData

@Dao
interface TeamsKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRemoteKey(keys: TeamsKeyDbData)

    @Query("DELETE FROM team_keys")
    suspend fun clearRemoteKeys()

    @Query("SELECT * FROM team_keys WHERE id = (SELECT MAX(id) FROM team_keys)")
    suspend fun getLastRemoteKey(): TeamsKeyDbData?
}