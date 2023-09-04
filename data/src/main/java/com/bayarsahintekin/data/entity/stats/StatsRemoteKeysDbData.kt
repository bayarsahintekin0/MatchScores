package com.bayarsahintekin.data.entity.stats

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stats_remote_keys")
data class StatsRemoteKeysDbData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val prevPage: Int?,
    val nextPage: Int?
)
