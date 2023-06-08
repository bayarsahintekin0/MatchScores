package com.bayarsahintekin.data.local.teams

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bayarsahintekin.data.entity.ListResponseDbData
import com.bayarsahintekin.data.entity.TeamsDbData

@Dao
interface TeamDao {
    @Query("SELECT * FROM list_response")
    fun getTeams(): ListResponseDbData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTeams(teams: ListResponseDbData)

    @Query("DELETE FROM teams")
    suspend fun clearTeams()
}