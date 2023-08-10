package com.bayarsahintekin.data.entity.players

import com.bayarsahintekin.data.entity.MetaData
import com.bayarsahintekin.data.entity.toDomain
import com.bayarsahintekin.domain.entity.PlayerListEntity

data class PlayerListData(
    var data: ArrayList<PlayerData> = arrayListOf(),
    var meta: MetaData? = null
)

fun PlayerListData.toDomain() :PlayerListEntity {
    return PlayerListEntity(
            data = data.map { data-> data.toDomain() },
            meta = meta?.toDomain()
        )

}
