package com.bayarsahintekin.data.mapper

import com.bayarsahintekin.data.entity.players.PlayersDbData
import com.bayarsahintekin.domain.entity.players.PlayerEntity

fun PlayerEntity.toDbData() = PlayersDbData(
    id = id,
    firstName = firstName,
    heightFeet = heightFeet,
    heightInches = heightInches,
    lastName = lastName,
    position = position,
    team = team?.toDbData(),
    weightPounds = weightPounds
)
