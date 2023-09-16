package com.bayarsahintekin.data.data

import com.bayarsahintekin.data.entity.players.PlayersDbData
import com.bayarsahintekin.data.entity.players.toDomain
import com.bayarsahintekin.data.entity.teams.TeamsDbData

object PlayersMockData {

    fun getPlayer() = PlayersDbData(
        id = 14,
        firstName = "Ike",
        heightFeet = "0",
        heightInches = "0",
        lastName = "Anigbogu",
        position = "C",
        team = TeamsDbData(
            id = 12,
            abbreviation = "IND",
            city = "Indiana",
            conference = "East",
            division = "Central",
            fullName = "Indiana Pacers",
            name = "Pacers"
        ),
        weightPounds = "0"
    )

    fun getPlayers() = listOf(
        PlayersDbData(
            id = 14,
            firstName = "Ike",
            heightFeet = "0",
            heightInches = "0",
            lastName = "Anigbogu",
            position = "C",
            team = TeamsDbData(
                id = 12,
                abbreviation = "IND",
                city = "Indiana",
                conference = "East",
                division = "Central",
                fullName = "Indiana Pacers",
                name = "Pacers"
            ),
            weightPounds = "0"
        ).toDomain(),
        PlayersDbData(
            id = 15,
            firstName = "Ike2",
            heightFeet = "0",
            heightInches = "0",
            lastName = "Anigbogu2",
            position = "C",
            team = TeamsDbData(
                id = 12,
                abbreviation = "IND",
                city = "Indiana",
                conference = "East",
                division = "Central",
                fullName = "Indiana Pacers",
                name = "Pacers"
            ),
            weightPounds = "0"
        ).toDomain()
    )
}