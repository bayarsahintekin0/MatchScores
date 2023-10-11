package com.bayarsahintekin.data.repository.teams

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.RemoteMediator
import androidx.paging.map
import com.bayarsahintekin.data.entity.stats.toDomain
import com.bayarsahintekin.data.entity.teams.toDomain
import com.bayarsahintekin.domain.entity.stats.StatsEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.entity.teams.TeamListEntity
import com.bayarsahintekin.domain.repository.TeamRepository
import com.bayarsahintekin.domain.utils.Result
import com.bayarsahintekin.domain.utils.onError
import com.bayarsahintekin.domain.utils.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TeamRepositoryImpl(
    private val remote: TeamDataSource.Remote,
    private val local: TeamDataSource.Local,
    private val remoteMediator: TeamRemoteMediator,
) : TeamRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getTeams(): Flow<PagingData<TeamEntity>> = Pager(
        config = PagingConfig(
            pageSize = 30,
            enablePlaceholders = false
        ),
        remoteMediator = remoteMediator,
        pagingSourceFactory = { local.teams() }
    ).flow.map { pagingData ->
        pagingData.map {
            it.toDomain()
        }
    }

    override suspend fun getTeam(id: String): Result<TeamEntity> = local.getTeam(id.toInt()).onSuccess {
        it
    }.onError {
        remote.getTeam(id)
    }

}