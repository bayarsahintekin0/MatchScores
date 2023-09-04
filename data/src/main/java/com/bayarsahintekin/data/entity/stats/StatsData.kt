package com.bayarsahintekin.data.entity.stats

import com.bayarsahintekin.data.entity.games.GameData
import com.bayarsahintekin.data.entity.games.toDomain
import com.bayarsahintekin.data.entity.players.PlayerData
import com.bayarsahintekin.data.entity.players.toDomain
import com.bayarsahintekin.data.entity.teams.TeamData
import com.bayarsahintekin.data.entity.teams.toDomain
import com.bayarsahintekin.domain.entity.StatsEntity

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
    val game: GameData,
    val min: String,
    val oreb: Int,
    val pf: Int,
    val player: PlayerData,
    val pts: Int,
    val reb: Int,
    val stl: Int,
    val team: TeamData,
    val turnover: Int
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