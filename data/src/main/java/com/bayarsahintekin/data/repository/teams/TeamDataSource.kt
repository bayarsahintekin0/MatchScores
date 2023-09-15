package com.bayarsahintekin.data.repository.teams

import androidx.paging.PagingSource
import com.bayarsahintekin.data.entity.teams.TeamsDbData
import com.bayarsahintekin.data.entity.teams.TeamsKeyDbData
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.entity.teams.TeamListEntity
import com.bayarsahintekin.domain.utils.Result

interface TeamDataSource {

    interface Remote {
        suspend fun getTeams(page: Int): Result<TeamListEntity>

        suspend fun getTeam(id: String): Result<TeamEntity>
    }

    interface Local {
        fun teams(): PagingSource<Int, TeamsDbData>
        suspend fun getTeams(): Result<List<TeamEntity>>
        suspend fun getTeam(teamId: Int): Result<TeamEntity>
        suspend fun saveTeams(teamEntities: List<TeamEntity>)
        suspend fun getLastRemoteKey(): TeamsKeyDbData?
        suspend fun saveRemoteKey(key: TeamsKeyDbData)
        suspend fun clearTeams()
        suspend fun clearRemoteKeys()
    }
}