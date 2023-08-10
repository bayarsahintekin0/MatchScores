package com.bayarsahintekin.data.remote

import com.bayarsahintekin.data.entity.players.PlayerListData
import com.bayarsahintekin.data.entity.teams.TeamData
import com.bayarsahintekin.data.entity.teams.TeamListData
import retrofit2.http.GET
import retrofit2.http.Path

interface ScoreServices {

    @GET("teams")
    suspend fun getAllTeams(): TeamListData

    @GET("teams/{id}")
    suspend fun getTeam(@Path("id") id: String): TeamData

    @GET("players")
    suspend fun getAllPlayers():PlayerListData
}