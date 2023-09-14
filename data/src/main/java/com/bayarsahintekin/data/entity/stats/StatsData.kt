package com.bayarsahintekin.data.entity.stats

import com.bayarsahintekin.data.entity.teams.TeamData
import com.bayarsahintekin.data.entity.teams.toDomain
import com.bayarsahintekin.domain.entity.stats.StatsEntity
import com.bayarsahintekin.domain.entity.stats.StatsGameEntity
import com.bayarsahintekin.domain.entity.stats.StatsPlayerEntity
import com.google.gson.annotations.SerializedName

data class StatsData(
    val id: Int,
    val ast: Int,
    val blk: Int,
    val dreb: Int,
    val fg3_pct: Float,
    val fg3a: Int,
    val fg3m: Int,
    val fg_pct: Float,
    val fga: Int,
    val fgm: Int,
    val ft_pct: Float,
    val fta: Int,
    val ftm: Int,
    val game: StatsGame,
    val min: String,
    val oreb: Int,
    val pf: Int,
    val player: StatsPlayerData,
    val pts: Int,
    val reb: Int,
    val stl: Int,
    val team: TeamData = TeamData(0, "", "", "", "", "", ""),
    val turnover: Int
)

data class StatsPlayerData(
    var id: Int = 0,
    @SerializedName("first_name")
    var firstName: String = "",
    @SerializedName("height_feed")
    var heightFeet: String = "",
    @SerializedName("height_inches")
    var heightInches: String? = "",
    @SerializedName("last_name")
    var lastName: String = "",
    var position: String = "",
    var team: Int = 1,
    @SerializedName("weight_pounds")
    var weightPounds: String = ""
)

data class StatsGame(
    val id: Int = 0,
    val date: String = "",
    val home_team_id: Int = 0,
    val home_team_score: Int = 0,
    val period: Int = 0,
    val postseason: Boolean = false,
    val season: Int = 0,
    val status: String = "",
    val time: String = "",
    val visitor_team_id: Int = 0,
    val visitor_team_score: Int = 0
)

fun StatsGame.toDomain() = StatsGameEntity(
    id,
    date,
    home_team_id,
    home_team_score,
    period,
    postseason,
    season,
    status,
    time,
    visitor_team_id,
    visitor_team_score
)

fun StatsPlayerData.toDomain() = StatsPlayerEntity(
    id = id,
    firstName = firstName,
    heightFeet = heightFeet,
    heightInches = heightInches,
    lastName = lastName,
    position = position,
    team = team,
    weightPounds = weightPounds
)


fun StatsData.toDomain() = StatsEntity(
    id = id,
    ast = ast,
    blk = blk,
    dreb = dreb,
    fg3_pct = fg3_pct,
    fg3a = fg3a,
    fg3m = fg3m,
    fg_pct = fg_pct,
    fga = fga,
    fgm = fgm,
    ft_pct = ft_pct,
    fta = fta,
    ftm = ftm,
    game = game.toDomain(),
    min = min,
    oreb = oreb,
    pf = pf,
    player = player.toDomain(),
    pts = pts,
    reb = reb,
    stl = stl,
    team = team.toDomain(),
    turnover = turnover
)