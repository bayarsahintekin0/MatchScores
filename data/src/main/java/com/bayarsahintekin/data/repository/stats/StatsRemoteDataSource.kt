package com.bayarsahintekin.data.repository.stats

import com.bayarsahintekin.data.entity.stats.StatsListData
import com.bayarsahintekin.data.entity.stats.toDomain
import com.bayarsahintekin.data.remote.ScoreServices
import com.bayarsahintekin.domain.entity.stats.StatsEntity
import com.bayarsahintekin.domain.entity.stats.StatsListEntity
import com.bayarsahintekin.domain.utils.Result

class StatsRemoteDataSource (private val scoreService: ScoreServices
) : StatsDataSource.Remote {
    override suspend fun getStats(page: Int,seasons:Int?,playerId:Int?): Result<StatsListEntity> =
        try {
            val result = scoreService.getAllStats(page,seasons,playerId)
            Result.Success(
                StatsListData(
                    data = result.data,
                    meta = result.meta
                ).toDomain()
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
}
