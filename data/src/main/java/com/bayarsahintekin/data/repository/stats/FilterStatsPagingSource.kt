package com.bayarsahintekin.data.repository.stats

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bayarsahintekin.domain.entity.stats.StatsEntity
import com.bayarsahintekin.domain.utils.getResult

const val STATS_FILTER_PAGE_INDEX = 0
class FilterStatsPagingSource(val season: Int?,val playerId: Int?,val remote: StatsDataSource.Remote): PagingSource<Int, StatsEntity>()  {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StatsEntity> {
        val page = params.key ?: STATS_FILTER_PAGE_INDEX

        return remote.getStats(page,season,playerId).getResult({
            LoadResult.Page(
                data = it.data.data,
                prevKey = if (page == STATS_FILTER_PAGE_INDEX) null else page - 1,
                nextKey = if (it.data.data.isEmpty()) null else page + 1
            )
        }, {
            LoadResult.Error(it.error)
        })
    }

    override fun getRefreshKey(state: PagingState<Int, StatsEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}