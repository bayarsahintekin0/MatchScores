package com.bayarsahintekin.data.entity.players

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players_remote_keys")
data class PlayersRemoteKeysDbData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val prevPage: Int?,
    val nextPage: Int?
)