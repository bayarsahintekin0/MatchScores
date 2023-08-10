package com.bayarsahintekin.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.bayarsahintekin.data.entity.teams.TeamsDbData
import com.bayarsahintekin.data.entity.teams.TeamsKeyDbData
import com.bayarsahintekin.domain.utils.getResult

private const val MOVIE_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class TeamRemoteMediator(
    private val local: TeamDataSource.Local,
    private val remote: TeamDataSource.Remote
) : RemoteMediator<Int, TeamsDbData>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, TeamsDbData>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> MOVIE_STARTING_PAGE_INDEX
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> local.getLastRemoteKey()?.nextPage ?: return MediatorResult.Success(endOfPaginationReached = true)
        }

        Log.d("XXX", "MovieRemoteMediator: load() called with: loadType = $loadType, page: $page, stateLastItem = ${state.isEmpty()}")

        // There was a lag in loading the first page; as a result, it jumps to the end of the pagination.
        if (state.isEmpty() && page == 2) return MediatorResult.Success(endOfPaginationReached = false)

        remote.getTeams().getResult({ successResult ->

            if (loadType == LoadType.REFRESH) {
                local.clearTeams()
                local.clearRemoteKeys()
            }

            val teamData = successResult.data

            val endOfPaginationReached = teamData.data.isEmpty()

            val prevPage = if (page == MOVIE_STARTING_PAGE_INDEX) null else page - 1
            val nextPage = if (endOfPaginationReached) null else page + 1

            val key = TeamsKeyDbData(prevPage = prevPage, nextPage = nextPage)

            local.saveTeams(teamData)
            local.saveRemoteKey(key)

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        }, { errorResult ->
            return MediatorResult.Error(errorResult.error)
        })
    }
}