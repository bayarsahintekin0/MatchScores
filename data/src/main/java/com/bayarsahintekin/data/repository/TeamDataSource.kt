package com.bayarsahintekin.data.repository

import com.bayarsahintekin.data.entity.TeamsKeyDbData
import com.bayarsahintekin.domain.entity.ListResponseEntity
import com.bayarsahintekin.domain.entity.TeamEntity
import com.bayarsahintekin.domain.utils.Result

interface TeamDataSource {

    interface Remote {
        suspend fun getTeams(): Result<ListResponseEntity>
    }

    interface Local {
        suspend fun getTeams(): Result<ListResponseEntity>
        suspend fun getLastRemoteKey(): TeamsKeyDbData?
        suspend fun saveTeams(teamEntities: ListResponseEntity)
        suspend fun saveRemoteKey(key: TeamsKeyDbData)
        suspend fun clearTeams()
        suspend fun clearRemoteKeys()
    }
}