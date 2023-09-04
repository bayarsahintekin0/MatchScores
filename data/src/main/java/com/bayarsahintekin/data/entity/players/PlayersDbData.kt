package com.bayarsahintekin.data.entity.players

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bayarsahintekin.data.entity.teams.toDomain
import com.bayarsahintekin.domain.entity.PlayerEntity
import com.bayarsahintekin.domain.entity.TeamEntity

@Entity(tableName = "players")
data class PlayersDbData(
    @PrimaryKey var id: Int? = 0,
    var firstName: String? = "",
    var heightFeet: String? = "",
    var heightInches: String? = "",
    var lastName: String? = "",
    var position: String? = "",
    var team: TeamEntity? = TeamEntity(0,"","","","","",""),
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

