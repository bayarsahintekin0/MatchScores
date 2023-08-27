package com.bayarsahintekin.data.entity.players

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bayarsahintekin.data.entity.teams.toDomain
import com.bayarsahintekin.domain.entity.PlayerEntity
import com.bayarsahintekin.domain.entity.TeamEntity

@Entity(tableName = "players")
data class PlayersDbData(
    @PrimaryKey var id: Int? = null,
    var firstName: String? = null,
    var heightFeet: String? = null,
    var heightInches: String? = null,
    var lastName: String? = null,
    var position: String? = null,
    var team: TeamEntity? = null,
    var weightPounds: String? = null
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

