package com.bayarsahintekin.domain.entity

data class StatsEntity(
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
    val game: GameEntity,
    val min: String,
    val oreb: Int,
    val pf: Int,
    val player: PlayerEntity,
    val pts: Int,
    val reb: Int,
    val stl: Int,
    val team: TeamEntity,
    val turnover: Int
)
