package com.bayarsahintekin.data.repository

import com.bayarsahintekin.data.exception.DataNotAvailableException
import com.bayarsahintekin.data.entity.ListResponse
import com.bayarsahintekin.data.entity.TeamData
import com.bayarsahintekin.data.entity.TeamsKeyDbData
import com.bayarsahintekin.data.entity.toDomain
import com.bayarsahintekin.data.local.teams.TeamDao
import com.bayarsahintekin.data.local.teams.TeamsKeyDao
import com.bayarsahintekin.data.mapper.toDbData
import com.bayarsahintekin.data.utils.DiskExecutor
import com.bayarsahintekin.domain.entity.ListResponseEntity
import com.bayarsahintekin.domain.entity.TeamEntity
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext
import com.bayarsahintekin.domain.utils.Result

class TeamsLocalDataSource(
    private val executor: DiskExecutor,
    private val teamDao: TeamDao,
    private val teamsKeyDao: TeamsKeyDao
):TeamDataSource.Local {

    override suspend fun getTeams(): Result<ListResponseEntity> =  withContext(executor.asCoroutineDispatcher()) {
        val team = teamDao.getTeams()
        return@withContext if (team != null) {
            Result.Success(
                ListResponse(
                    team.data.map { it.toDomain() },
                    team.meta.toDomain()
                ).toDomain()
            )
        } else {
            Result.Error(DataNotAvailableException())
        }
    }

    override suspend fun getLastRemoteKey(): TeamsKeyDbData? = withContext(executor.asCoroutineDispatcher()){
        teamsKeyDao.getLastRemoteKey()
    }

    override suspend fun saveRemoteKey(key: TeamsKeyDbData) = withContext(executor.asCoroutineDispatcher()){
        teamsKeyDao.saveRemoteKey(key)
    }

    override suspend fun clearTeams() = withContext(executor.asCoroutineDispatcher()){
        teamDao.clearTeams()
    }

    override suspend fun clearRemoteKeys() = withContext(executor.asCoroutineDispatcher()){
        teamsKeyDao.clearRemoteKeys()
    }

    override suspend fun saveTeams(teamEntities: ListResponseEntity) = withContext(executor.asCoroutineDispatcher()){
        teamDao.saveTeams(teamEntities.toDbData())
    }

    override suspend fun getTeam(id: String): Result<TeamEntity> = withContext(executor.asCoroutineDispatcher()){
        val team = teamDao.getTeam(id).toDomain()
        return@withContext Result.Success(team.toDomain())
    }
    override suspend fun deleteTeam(id: String)  = withContext(executor.asCoroutineDispatcher()){
        teamDao.deleteTeam(id)
    }
}