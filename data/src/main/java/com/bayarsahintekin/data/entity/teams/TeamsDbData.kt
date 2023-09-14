package com.bayarsahintekin.data.entity.teams

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bayarsahintekin.domain.entity.teams.TeamEntity

@Entity(tableName = "teams")
data class TeamsDbData(
    @ColumnInfo("id")
    @PrimaryKey var id: Int = 0,
    @ColumnInfo("abbreviation")
    var abbreviation: String= "",
    @ColumnInfo("city")
    var city: String= "",
    @ColumnInfo("conference")
    var conference: String = "",
    @ColumnInfo("division")
    var division: String = "",
    @ColumnInfo("fullName")
    var fullName: String = "",
    @ColumnInfo("name")
    var name: String = ""
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