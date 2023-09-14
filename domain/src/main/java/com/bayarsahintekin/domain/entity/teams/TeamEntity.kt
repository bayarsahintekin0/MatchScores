package com.bayarsahintekin.domain.entity.teams

data class TeamEntity(
    val id: Int,
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
    val fullName: String,
    val name: String
)