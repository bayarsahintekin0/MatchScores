package com.bayarsahintekin.domain.entity

class StatsGameEntity(
    val id: Int,
    val date: String,
    val home_team_id: Int,
    val home_team_score: Int,
    val period: Int,
    val postseason: Boolean,
    val season: Int,
    val status: String,
    val time: String,
    val visitor_team_id: Int,
    val visitor_team_score: Int
)