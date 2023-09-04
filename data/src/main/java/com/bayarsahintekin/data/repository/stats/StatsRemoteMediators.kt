package com.bayarsahintekin.data.repository.stats

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.bayarsahintekin.data.entity.games.GameDbData
import com.bayarsahintekin.data.entity.games.GamesRemoteKeysDbData
import com.bayarsahintekin.data.entity.stats.StatsDbData
import com.bayarsahintekin.data.entity.stats.StatsRemoteKeysDbData
import com.bayarsahintekin.domain.utils.getResult

@OptIn(ExperimentalPagingApi::class)
class StatsRemoteMediators (
    private val local: StatsDataSource.Local,
    private val remote: StatsDataSource.Remote
) : RemoteMediator<Int, StatsDbData>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, StatsDbData>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> local.getLastRemoteKey()?.nextPage ?: return MediatorResult.Success(endOfPaginationReached = true)
        }

        Log.d("XXX", "MovieRemoteMediator: load() called with: loadType = $loadType, page: $page, stateLastItem = ${state.isEmpty()}")

        // There was a lag in loading the first page; as a result, it jumps to the end of the pagination.
        if (state.isEmpty() && page == 2) return MediatorResult.Success(endOfPaginationReached = false)

        remote.getStats(page).getResult({ successResult ->

            if (loadType == LoadType.REFRESH) {
                local.clearStats()
                local.clearRemoteKeys()
            }

            val players = successResult.data

            val key = StatsRemoteKeysDbData(prevPage = players.meta?.currentPage?.minus(1), nextPage = players.meta?.nextPage)

            local.saveStats(players.data)
            local.saveRemoteKey(key)

            return MediatorResult.Success(endOfPaginationReached = players.meta?.totalPages == players.meta?.currentPage)
        }, { errorResult ->
            return MediatorResult.Error(errorResult.error)
        })
    }
}
