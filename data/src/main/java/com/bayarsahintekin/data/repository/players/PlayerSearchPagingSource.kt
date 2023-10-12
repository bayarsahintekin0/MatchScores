package com.bayarsahintekin.data.repository.players

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.utils.getResult

const val STARTING_PAGE_INDEX = 1
class PlayerSearchPagingSource(
    private val query: String,
    private val remote: PlayerDataSource.Remote
) : PagingSource<Int, PlayerEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlayerEntity> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return remote.search(query, page).getResult({
            LoadResult.Page(
                data = it.data.data,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (it.data.data.isEmpty()) null else page + 1
            )
        }, {
            LoadResult.Error(it.error)
        })
    }

    override fun getRefreshKey(state: PagingState<Int, PlayerEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}