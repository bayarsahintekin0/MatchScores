package com.bayarsahintekin.data.repository.games

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.bayarsahintekin.data.entity.games.GameDbData
import com.bayarsahintekin.data.entity.games.GamesRemoteKeysDbData
import com.bayarsahintekin.domain.utils.getResult

@OptIn(ExperimentalPagingApi::class)
class GameRemoteMediators (
    private val local: GameDataSource.Local,
    private val remote: GameDataSource.Remote
) : RemoteMediator<Int, GameDbData>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, GameDbData>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> local.getLastRemoteKey()?.nextPage ?: return MediatorResult.Success(endOfPaginationReached = true)
        }

        Log.d("XXX", "MovieRemoteMediator: load() called with: loadType = $loadType, page: $page, stateLastItem = ${state.isEmpty()}")

        // There was a lag in loading the first page; as a result, it jumps to the end of the pagination.
        if (state.isEmpty() && page == 2) return MediatorResult.Success(endOfPaginationReached = false)

        remote.getGames(page).getResult({ successResult ->

            if (loadType == LoadType.REFRESH) {
                local.clearGames()
                local.clearRemoteKeys()
            }

            val players = successResult.data

            val key = GamesRemoteKeysDbData(prevPage = players.meta?.currentPage?.minus(1), nextPage = players.meta?.nextPage)

            local.saveGames(players.data)
            local.saveRemoteKey(key)

            return MediatorResult.Success(endOfPaginationReached = players.meta?.totalPages == players.meta?.currentPage)
        }, { errorResult ->
            return MediatorResult.Error(errorResult.error)
        })
    }
}
