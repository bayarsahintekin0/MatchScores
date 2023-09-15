package com.bayarsahintekin.domain.entity.stats

import com.bayarsahintekin.domain.entity.teams.TeamEntity

data class StatsEntity(
    val id: Int?,
    val ast: Int?,
    val blk: Int?,
    val dreb: Int?,
    val fg3_pct: Float?,
    val fg3a: Int?,
    val fg3m: Int?,
    val fg_pct: Float?,
    val fga: Int?,
    val fgm: Int?,
    val ft_pct: Float?,
    val fta: Int?,
    val ftm: Int?,
    val game: StatsGameEntity,
    val min: String? = "0",
    val oreb: Int?,
    val pf: Int?,
    val player: StatsPlayerEntity,
    val pts: Int?,
    val reb: Int?,
    val stl: Int?,
    val team: TeamEntity,
    val turnover: Int?
)
