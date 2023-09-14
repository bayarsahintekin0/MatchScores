package com.bayarsahintekin.data.entity.games

import com.bayarsahintekin.data.entity.teams.TeamData
import com.bayarsahintekin.data.entity.teams.toDomain
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.google.gson.annotations.SerializedName

data class GameData(
    val id: Int,
    val date: String,
    @SerializedName("home_team") val homeTeam: TeamData,
    @SerializedName("home_team_score") val homeTeamScore: Int,
    val period: Int,
    val postseason: Boolean,
    val season: Int,
    val status: String,
    val time: String,
    @SerializedName("visitor_team") val visitorTeam: TeamData,
    @SerializedName("visitor_team_score") val visitorTeamScore: Int
)

fun GameData.toDomain() = GameEntity(
    id = id,
    date = date,
    homeTeam = homeTeam.toDomain(),
    homeTeamScore= homeTeamScore,
    period = period,
    postseason = postseason,
    season = season,
    status = status,
    time = time,
    visitorTeam = visitorTeam.toDomain(),
    visitorTeamScore = visitorTeamScore
)