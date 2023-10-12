package com.bayarsahintekin.data.remote

import com.bayarsahintekin.data.entity.games.GameListData
import com.bayarsahintekin.data.entity.players.PlayerData
import com.bayarsahintekin.data.entity.players.PlayerListData
import com.bayarsahintekin.data.entity.stats.StatsListData
import com.bayarsahintekin.data.entity.teams.TeamData
import com.bayarsahintekin.data.entity.teams.TeamListData
import com.bayarsahintekin.domain.entity.games.GameListEntity
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.utils.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScoreServices {

    @GET("teams")
    suspend fun getAllTeams(@Query("page") page: Int = 1): TeamListData

    @GET("teams/{id}")
    suspend fun getTeam(@Path("id") id: String): TeamData

    @GET("players")
    suspend fun getAllPlayers(@Query("page") page: Int = 1):PlayerListData

    @GET("players")
    suspend fun searchPlayers(@Query("page") page: Int= 1,@Query("search") query: String? = null ):PlayerListData

    @GET("players")
    suspend fun getPlayerById(@Path("id") id: String): PlayerData

    @GET("games")
    suspend fun getAllGames(@Query("page") page: Int = 1):GameListData

    @GET("games")
    suspend fun getFilterGames(@Query("page") page: Int = 1,
                               @Query("team_ids") teamIdList: List<Int>,
                               @Query("seasons") seasons: List<Int>): GameListData

    @GET("stats")
    suspend fun getAllStats(@Query("page") page: Int = 1,
                            @Query("seasons[]") season: Int?,
                            @Query("player_ids[]") playerId: Int?):StatsListData

    @GET("stats")
    suspend fun getStatsByFilter(@Query("page") page: Int = 1,
                                 ):StatsListData
}