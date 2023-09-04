package com.bayarsahintekin.data.local.stats

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bayarsahintekin.data.entity.stats.StatsDbData

@Dao
interface StatsDao {
    @Query("SELECT * FROM stats ORDER BY id")
    fun stats(): PagingSource<Int, StatsDbData>

    @Query("SELECT * FROM stats")
    fun getStats(): List<StatsDbData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveStats(stats: List<StatsDbData>)

    @Query("SELECT * FROM stats WHERE id = :statsId")
    fun getStat(statsId: Int): StatsDbData

    @Query("DELETE FROM stats")
    fun clearStats()

}