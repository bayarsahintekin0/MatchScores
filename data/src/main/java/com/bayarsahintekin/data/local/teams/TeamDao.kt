package com.bayarsahintekin.data.local.teams

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bayarsahintekin.data.entity.stats.StatsDbData
import com.bayarsahintekin.data.entity.teams.ListResponseDbData
import com.bayarsahintekin.data.entity.teams.TeamsDbData

@Dao
interface TeamDao {

    @Query("SELECT * FROM teams ORDER BY id")
    fun teams(): PagingSource<Int, TeamsDbData>

    @Query("SELECT * FROM teams")
    fun getTeams(): List<TeamsDbData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTeams(teams: List<TeamsDbData>)

    @Query("SELECT * FROM teams WHERE id = :teamId")
    fun getTeam(teamId: Int): TeamsDbData

    @Query("DELETE FROM teams")
    fun clearTeams()
}