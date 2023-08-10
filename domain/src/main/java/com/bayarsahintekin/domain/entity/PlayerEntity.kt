package com.bayarsahintekin.domain.entity

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
