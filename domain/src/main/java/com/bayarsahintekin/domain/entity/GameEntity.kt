package com.bayarsahintekin.domain.entity

class GameEntity (
    val id: Int,
    val date: String,
    val homeTeam: TeamEntity,
    val homeTeamScore: Int,
    val period: Int,
    val postseason: Boolean,
    val season: Int,
    val status: String,
    val time: String,
    val visitorTeam: TeamEntity,
    val visitorTeamScore: Int)