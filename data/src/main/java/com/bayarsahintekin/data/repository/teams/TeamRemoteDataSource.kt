package com.bayarsahintekin.data.repository.teams

import com.bayarsahintekin.data.entity.teams.TeamData
import com.bayarsahintekin.data.entity.teams.TeamListData
import com.bayarsahintekin.data.entity.teams.toDomain
import com.bayarsahintekin.data.remote.ScoreServices
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.entity.teams.TeamListEntity
import com.bayarsahintekin.domain.utils.Result

/**
 * Created by Ali Asadi on 13/05/2020
 */
class TeamRemoteDataSource(
    private val scoreService: ScoreServices
) : TeamDataSource.Remote {
    override suspend fun getTeams(page: Int): Result<TeamListEntity> =
        try {
            val result = scoreService.getAllTeams(page)
            Result.Success(
                TeamListData(
                    data = result.data,
                    meta = result.meta
                ).toDomain()
            )
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun getTeam(id: String): Result<TeamEntity> =
        try {
            val result = scoreService.getTeam(id)
            Result.Success(
                TeamData(
                    id = result.id,
                    abbreviation = result.abbreviation,
                    city = result.city,
                    conference = result.conference,
                    division = result.division,
                    fullName = result.fullName,
                    name = result.name
                ).toDomain()
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
}
