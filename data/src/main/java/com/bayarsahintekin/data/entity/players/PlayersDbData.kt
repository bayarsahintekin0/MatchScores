package com.bayarsahintekin.data.entity.players

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity

@Entity(tableName = "players")
data class PlayersDbData(
    @PrimaryKey var id: Int? = 0,
    @ColumnInfo("first_name")
    var firstName: String? = "",
    @ColumnInfo(name = "height_feet")
    var heightFeet: String? = "",
    @ColumnInfo(name = "height_inches")
    var heightInches: String? = "",
    @ColumnInfo(name = "last_name")
    var lastName: String? = "",
    var position: String? = "",
    var team: TeamEntity? = TeamEntity(0,"","","","","",""),
    @ColumnInfo(name = "weight_pounds")
    var weightPounds: String? = ""
)

fun PlayersDbData.toDomain() = PlayerEntity(
    id = id,
    firstName = firstName,
    heightFeet = heightFeet,
    heightInches = heightInches,
    lastName = lastName,
    position = position,
    team = team,
    weightPounds = weightPounds
)

