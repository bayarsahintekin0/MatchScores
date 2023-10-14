package com.bayarsahintekin.data.repository.games

import com.bayarsahintekin.data.entity.games.GameListData
import com.bayarsahintekin.data.entity.games.toDomain
import com.bayarsahintekin.data.remote.ScoreServices
import com.bayarsahintekin.domain.entity.games.GameListEntity
import com.bayarsahintekin.domain.utils.Result

class GameRemoteDataSource ( private val scoreService: ScoreServices
) : GameDataSource.Remote {

    override suspend fun getGames(page: Int): Result<GameListEntity> =
        try {
            val result = scoreService.getAllGames(page)
            Result.Success(
                GameListData(
                    data = result.data,
                    meta = result.meta
                ).toDomain()
            )
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun getFilterGames(page:Int, teamId: Int?, season: Int?): Result<GameListEntity> =
        try {
            val result = scoreService.getFilterGames(page,teamId, season)
            Result.Success(
                GameListData(
                    data = result.data,
                    meta = result.meta
                ).toDomain()
            )
        } catch (e: Exception){
            Result.Error(e)
        }

}
