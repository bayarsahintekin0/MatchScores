package com.bayarsahintekin.data.repository.teams

import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.entity.teams.TeamListEntity
import com.bayarsahintekin.domain.repository.TeamRepository
import com.bayarsahintekin.domain.utils.Result

class TeamRepositoryImpl(
    private val remote: TeamDataSource.Remote,
    private val local: TeamDataSource.Local,
    //private val remoteMediator: TeamRemoteMediator,
) : TeamRepository {

    override suspend fun getTeams(): Result<TeamListEntity> = remote.getTeams()

    override suspend fun getTeam(id: String): Result<TeamEntity> = remote.getTeam(id)

}