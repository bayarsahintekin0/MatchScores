package com.bayarsahintekin.data.entity.teams

import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.google.gson.annotations.SerializedName

data class TeamData(
    @SerializedName("id") val id: Int,
    @SerializedName("abbreviation") val abbreviation: String = "",
    @SerializedName("city") val city: String = "",
    @SerializedName("conference") val conference: String = "",
    @SerializedName("division") val division: String = "",
    @SerializedName("full_name") val fullName: String = "",
    @SerializedName("name") val name: String = ""
)

fun TeamData.toDomain() = TeamEntity(
    id = id,
    abbreviation = abbreviation,
    city = city,
    conference = conference,
    division = division,
    fullName = fullName,
    name = name
)