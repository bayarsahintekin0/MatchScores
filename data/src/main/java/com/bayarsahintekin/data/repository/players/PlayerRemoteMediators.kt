package com.bayarsahintekin.data.repository.players

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.bayarsahintekin.data.entity.players.PlayersDbData
import com.bayarsahintekin.data.entity.players.PlayersRemoteKeysDbData
import com.bayarsahintekin.domain.utils.getResult

@OptIn(ExperimentalPagingApi::class)
class PlayerRemoteMediators(
    private val local: PlayerDataSource.Local,
    private val remote: PlayerDataSource.Remote
) : RemoteMediator<Int, PlayersDbData>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, PlayersDbData>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> local.getLastRemoteKey()?.nextPage ?: return MediatorResult.Success(endOfPaginationReached = true)
        }

        Log.d("XXX", "MovieRemoteMediator: load() called with: loadType = $loadType, page: $page, stateLastItem = ${state.isEmpty()}")

        // There was a lag in loading the first page; as a result, it jumps to the end of the pagination.
        if (state.isEmpty() && page == 2) return MediatorResult.Success(endOfPaginationReached = false)

        remote.getPlayers(page).getResult({ successResult ->

            if (loadType == LoadType.REFRESH) {
                local.clearPlayers()
                local.clearRemoteKeys()
            }

            val players = successResult.data

            val key = PlayersRemoteKeysDbData(prevPage = players.meta?.currentPage?.minus(1), nextPage = players.meta?.nextPage)

            local.savePlayers(players.data)
            local.saveRemoteKey(key)

            return MediatorResult.Success(endOfPaginationReached = players.meta?.totalPages == players.meta?.currentPage)
        }, { errorResult ->
            return MediatorResult.Error(errorResult.error)
        })
    }
}
