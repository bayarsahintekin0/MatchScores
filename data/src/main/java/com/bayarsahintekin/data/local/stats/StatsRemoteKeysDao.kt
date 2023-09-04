package com.bayarsahintekin.data.local.stats

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bayarsahintekin.data.entity.stats.StatsRemoteKeysDbData

@Dao
interface StatsRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRemoteKey(keys: StatsRemoteKeysDbData)

    @Query("SELECT * FROM stats_remote_keys WHERE id=:id")
    fun getRemoteKeyByStatId(id: Int): StatsRemoteKeysDbData?

    @Query("DELETE FROM stats_remote_keys")
    fun clearRemoteKeys()

    @Query("SELECT * FROM stats_remote_keys WHERE id = (SELECT MAX(id) FROM stats_remote_keys)")
    fun getLastRemoteKey(): StatsRemoteKeysDbData?

}