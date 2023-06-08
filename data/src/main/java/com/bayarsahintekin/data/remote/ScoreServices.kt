package com.bayarsahintekin.data.remote

import com.bayarsahintekin.data.entity.ListResponse
import com.bayarsahintekin.data.entity.TeamData
import retrofit2.http.GET

interface ScoreServices {

    @GET("teams")
    suspend fun getAllTeams():ListResponse
}