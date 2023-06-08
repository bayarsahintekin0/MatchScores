package com.bayarsahintekin.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team_keys")
data class TeamsKeyDbData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val prevPage: Int?,
    val nextPage: Int?
)