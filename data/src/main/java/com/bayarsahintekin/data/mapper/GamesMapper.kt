package com.bayarsahintekin.data.mapper

import com.bayarsahintekin.data.entity.games.GameDbData
import com.bayarsahintekin.domain.entity.GameEntity

fun GameEntity.toDbData() = GameDbData(
    id = id,
    date = date,
    homeTeam = homeTeam,
    homeTeamScore= homeTeamScore,
    period = period,
    postseason = postseason,
    season = season,
    status = status,
    time = time,
    visitorTeam = visitorTeam,
    visitorTeamScore = visitorTeamScore
)

