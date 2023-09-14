package com.bayarsahintekin.data.repository.teams

import androidx.paging.PagingSource
import com.bayarsahintekin.data.entity.players.PlayersDbData
import com.bayarsahintekin.data.entity.players.PlayersRemoteKeysDbData
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.entity.teams.TeamListEntity
import com.bayarsahintekin.domain.utils.Result

interface TeamDataSource {

    interface Remote {
        suspend fun getTeams(): Result<TeamListEntity>

        suspend fun getTeam(id: String): Result<TeamEntity>
    }

    interface Local {
        fun players(): PagingSource<Int, PlayersDbData>
        suspend fun getPlayers(): Result<List<PlayerEntity>>
        suspend fun getPlayer(playerId: Int): Result<PlayerEntity>
        suspend fun savePlayers(playerEntities: List<PlayerEntity>)
        suspend fun getLastRemoteKey(): PlayersRemoteKeysDbData?
        suspend fun saveRemoteKey(key: PlayersRemoteKeysDbData)
        suspend fun clearPlayers()
        suspend fun clearRemoteKeys()
    }
}