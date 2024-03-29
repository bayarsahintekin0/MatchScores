package com.bayarsahintekin.data.entity.players

import com.bayarsahintekin.data.entity.MetaData
import com.bayarsahintekin.data.entity.toDomain
import com.bayarsahintekin.domain.entity.players.PlayerListEntity

data class PlayerListData(
    var data: List<PlayerData> = arrayListOf(),
    var meta: MetaData? = null
)

fun PlayerListData.toDomain() : PlayerListEntity {
    return PlayerListEntity(
            data = data.map { it.toDomain() },
            meta = meta?.toDomain()
        )

}
