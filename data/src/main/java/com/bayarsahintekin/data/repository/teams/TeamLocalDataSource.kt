package com.bayarsahintekin.data.repository.teams

import androidx.paging.PagingSource
import com.bayarsahintekin.data.entity.players.PlayersDbData
import com.bayarsahintekin.data.entity.players.PlayersRemoteKeysDbData
import com.bayarsahintekin.data.entity.stats.toDomain
import com.bayarsahintekin.data.entity.teams.TeamListData
import com.bayarsahintekin.data.entity.teams.TeamsDbData
import com.bayarsahintekin.data.entity.teams.TeamsKeyDbData
import com.bayarsahintekin.data.entity.teams.toDomain
import com.bayarsahintekin.data.entity.toDomain
import com.bayarsahintekin.data.exception.DataNotAvailableException
import com.bayarsahintekin.data.local.teams.TeamDao
import com.bayarsahintekin.data.local.teams.TeamsKeyDao
import com.bayarsahintekin.data.mapper.toDbData
import com.bayarsahintekin.data.utils.DiskExecutor
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.entity.teams.TeamListEntity
import com.bayarsahintekin.domain.utils.Result
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext

class TeamLocalDataSource(
    private val executor: DiskExecutor,
    private val teamDao: TeamDao,
    private val teamsKeyDao: TeamsKeyDao
): TeamDataSource.Local {
    override fun teams(): PagingSource<Int, TeamsDbData> = teamDao.teams()

    override suspend fun getTeams(): Result<List<TeamEntity>> = withContext(executor.asCoroutineDispatcher()) {
        val teams = teamDao.getTeams()
        return@withContext if (teams.isEmpty()) {
            Result.Success(teams.map { it.toDomain() })
        } else {
            Result.Error(DataNotAvailableException())
        }
    }

    override suspend fun getTeam(teamId: Int): Result<TeamEntity> = withContext(executor.asCoroutineDispatcher()) {
        return@withContext teamDao.getTeam(teamId = teamId).let {
            Result.Success(it.toDomain())
        }
    }

    override suspend fun saveTeams(teamEntities: List<TeamEntity>) {
        teamDao.saveTeams(teamEntities.map { it.toDbData() })
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

    override suspend fun clearRemoteKeys() {
        teamsKeyDao.clearRemoteKeys()
    }
}