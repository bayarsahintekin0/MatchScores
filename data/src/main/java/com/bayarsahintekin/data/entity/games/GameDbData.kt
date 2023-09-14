package com.bayarsahintekin.data.entity.games

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity

@Entity(tableName = "games")
data class GameDbData(
    @PrimaryKey var id: Int = 0,
    val date: String = "",
    @ColumnInfo("home_team") val homeTeam: TeamEntity = TeamEntity(0,"","","","","",""),
    @ColumnInfo("home_team_score") val homeTeamScore: Int = 0,
    val period: Int = 1,
    val postseason: Boolean = false,
    val season: Int = 2012,
    val status: String = "",
    val time: String = "",
    @ColumnInfo("visitor_team") val visitorTeam: TeamEntity = TeamEntity(0,"","","","","",""),
    @ColumnInfo("visitor_team_score") val visitorTeamScore: Int = 0
)

fun GameDbData.toDomain() = GameEntity(
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