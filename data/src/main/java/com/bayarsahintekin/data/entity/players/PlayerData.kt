package com.bayarsahintekin.data.entity.players

import com.bayarsahintekin.data.entity.teams.TeamData
import com.bayarsahintekin.data.entity.teams.toDomain
import com.bayarsahintekin.domain.entity.PlayerEntity

data class PlayerData(
    var id: Int? = null,
    var firstName: String? = null,
    var heightFeet: String? = null,
    var heightInches: String? = null,
    var lastName: String? = null,
    var position: String? = null,
    var team: TeamData? = null,
    var weightPounds: String? = null
)

fun PlayerData.toDomain() = PlayerEntity(
    id = id,
    firstName = firstName,
    heightFeet = heightFeet,
    heightInches = heightInches,
    lastName = lastName,
    position = position,
    team = team?.toDomain(),
    weightPounds = weightPounds
)

