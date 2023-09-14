package com.bayarsahintekin.data.repository.players

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.bayarsahintekin.data.entity.players.PlayersDbData
import com.bayarsahintekin.data.entity.players.PlayersRemoteKeysDbData
import com.bayarsahintekin.domain.utils.getResult

const val MOVIE_STARTING_PAGE_INDEX = 1
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

            val players = successResult.data.data

            val endOfPaginationReached = players.isEmpty()

            val prevPage = if (page == MOVIE_STARTING_PAGE_INDEX) null else page - 1
            val nextPage = if (endOfPaginationReached) null else page + 1

            val key = PlayersRemoteKeysDbData(prevPage = prevPage, nextPage = nextPage)

            local.savePlayers(players)
            local.saveRemoteKey(key)

            Log.i("***","prev:$prevPage next:$nextPage ,endOf:$endOfPaginationReached")

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        }, { errorResult ->
            return MediatorResult.Error(errorResult.error)
        })
    }
}
