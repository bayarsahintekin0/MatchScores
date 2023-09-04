package com.bayarsahintekin.data.entity.games

import com.bayarsahintekin.data.entity.MetaData
import com.bayarsahintekin.data.entity.toDomain
import com.bayarsahintekin.domain.entity.GameListEntity
data class GameListData(
    val data: List<GameData>,
    val meta: MetaData
)

fun GameListData.toDomain() : GameListEntity {
    return GameListEntity(
        data = data.map { it.toDomain() },
        meta = meta.toDomain()
    )

}