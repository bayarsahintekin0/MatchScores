package com.bayarsahintekin.data.repository

import com.bayarsahintekin.data.entity.teams.TeamsKeyDbData
import com.bayarsahintekin.domain.entity.PlayerListEntity
import com.bayarsahintekin.domain.entity.TeamEntity
import com.bayarsahintekin.domain.entity.TeamListEntity
import com.bayarsahintekin.domain.utils.Result

interface TeamDataSource {

    interface Remote {
        suspend fun getTeams(): Result<TeamListEntity>

        suspend fun getTeam(id: String): Result<TeamEntity>
    }

    interface Local {
        suspend fun getTeams(): Result<TeamListEntity>
        suspend fun getTeam(id: String):Result<TeamEntity>
        suspend fun deleteTeam(id: String)
        suspend fun getLastRemoteKey(): TeamsKeyDbData?
        suspend fun saveTeams(teamEntities: TeamListEntity)
        suspend fun saveRemoteKey(key: TeamsKeyDbData)
        suspend fun clearTeams()
        suspend fun clearRemoteKeys()
    }
}