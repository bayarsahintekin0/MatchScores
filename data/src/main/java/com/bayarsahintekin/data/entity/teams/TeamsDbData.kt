package com.bayarsahintekin.data.entity.teams

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bayarsahintekin.domain.entity.TeamEntity

@Entity(tableName = "teams")
data class TeamsDbData(
    @ColumnInfo("id")
    @PrimaryKey val id: Int = 0,
    @ColumnInfo("abbreviation")
    val abbreviation: String= "",
    @ColumnInfo("city")
    val city: String= "",
    @ColumnInfo("conference")
    val conference: String = "",
    @ColumnInfo("division")
    val division: String = "",
    @ColumnInfo("fullName")
    val fullName: String = "",
    @ColumnInfo("name")
    val name: String = ""
)

fun TeamsDbData.toDomain() = TeamData(
    id = id,
    abbreviation = abbreviation,
    city = city,
    conference = conference,
    division = division,
    fullName = fullName,
    name = name
)
fun TeamsDbData.toData() = TeamEntity(
    id = id,
    abbreviation = abbreviation,
    city = city,
    conference = conference,
    division = division,
    fullName = fullName,
    name = name
)