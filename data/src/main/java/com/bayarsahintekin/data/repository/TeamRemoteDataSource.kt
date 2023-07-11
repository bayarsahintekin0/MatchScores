package com.bayarsahintekin.data.repository

import com.bayarsahintekin.data.entity.ListResponse
import com.bayarsahintekin.data.entity.TeamData
import com.bayarsahintekin.data.entity.toDomain
import com.bayarsahintekin.data.remote.ScoreServices
import com.bayarsahintekin.domain.entity.ListResponseEntity
import com.bayarsahintekin.domain.entity.TeamEntity
import com.bayarsahintekin.domain.utils.Result

/**
 * Created by Ali Asadi on 13/05/2020
 */
class TeamRemoteDataSource(
    private val scoreService: ScoreServices
) : TeamDataSource.Remote {
    override suspend fun getTeams(): Result<ListResponseEntity> =
        try {
            val result = scoreService.getAllTeams()
            Result.Success(
                ListResponse(
                    data = result.data,
                    meta = result.meta
                ).toDomain())
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun getTeam(id: String): Result<TeamEntity> =
        try {
           val result = scoreService.getTeam(id)
            Result.Success(
                TeamData(id = result.id,
                abbreviation = result.abbreviation,
                city = result.city,
                conference = result.conference,
                division = result.division,
                fullName = result.fullName,
                name = result.name).toDomain()
            )
        }catch (e: Exception){
            Result.Error(e)
        }
}
