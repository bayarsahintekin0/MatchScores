package com.bayarsahintekin.data.entity.games

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games_remote_keys")
data class GamesRemoteKeysDbData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val prevPage: Int?,
    val nextPage: Int?
)