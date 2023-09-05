package com.bayarsahintekin.data.entity.players

import com.bayarsahintekin.data.entity.teams.TeamData
import com.bayarsahintekin.data.entity.teams.toDomain
import com.bayarsahintekin.domain.entity.PlayerEntity
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



data class PlayerData(
    var id: Int? = null,
    @SerializedName("first_name")
    var firstName: String? = "",
    @SerializedName("height_feed")
    var heightFeet: String? = null,
    @SerializedName("height_inches")
    var heightInches: String? = null,
    @SerializedName("last_name")
    var lastName: String? = null,
    var position: String? = null,
    var team: TeamData? = null,
    @SerializedName("weight_pounds")
    var weightPounds: String? = null
)

fun PlayerData.toDomain() = PlayerEntity(
    id = id,
    firstName = firstName,
    heightFeet = heightFeet,
    heightInches = heightInches,
    lastName = lastName,
    position = position,
    team = team?.toDomain(),
    weightPounds = weightPounds
)

