package com.bayarsahintekin.domain.entity.players

import com.bayarsahintekin.domain.entity.teams.TeamEntity

data class PlayerEntity(
    var id: Int?,
    var firstName: String?,
    var heightFeet: String?,
    var heightInches: String?,
    var lastName: String?,
    var position: String?,
    var team: TeamEntity?,
    var weightPounds: String?
)
