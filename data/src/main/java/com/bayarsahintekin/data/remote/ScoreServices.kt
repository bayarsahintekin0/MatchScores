package com.bayarsahintekin.data.remote

import com.bayarsahintekin.data.entity.ListResponse
import com.bayarsahintekin.data.entity.TeamData
import retrofit2.http.GET
import retrofit2.http.Path

interface ScoreServices {

    @GET("teams")
    suspend fun getAllTeams():ListResponse

    @GET("teams/{id}")
    suspend fun getTeam(@Path("id") id: String):TeamData
}