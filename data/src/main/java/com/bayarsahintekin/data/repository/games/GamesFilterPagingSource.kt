package com.bayarsahintekin.data.repository.games

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.utils.getResult

private const val STARTING_GAME_FILTER_PAGE_INDEX = 1

class GamesFilterPagingSource(
    private val teamIdList:List<Int>,
    private val seasons:List<Int>,
    private val remote: GameDataSource.Remote
) : PagingSource<Int, GameEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameEntity> {
        val page = params.key ?: STARTING_GAME_FILTER_PAGE_INDEX

        return remote.getFilterGames(page, teamIdList, seasons).getResult({
            LoadResult.Page(
                data = it.data.data,
                prevKey = if (page == STARTING_GAME_FILTER_PAGE_INDEX) null else page - 1,
                nextKey = if (it.data.data.isEmpty()) null else page + 1
            )
        }, {
            LoadResult.Error(it.error)
        })
    }

    override fun getRefreshKey(state: PagingState<Int, GameEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}